package com.example.projectstem.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projectstem.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Entity(tableName = "group_language")
data class Group(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="group_id") var group_id: Int,
    @ColumnInfo(name = "language1") var language1 : String,
    @ColumnInfo(name = "language2") var language2: String,
)

@Entity(foreignKeys = [ForeignKey(entity = Group::class,
    parentColumns = arrayOf("group_id"),
    childColumns = arrayOf("group_language_id"),
    onDelete = ForeignKey.CASCADE)],
    tableName = "words"
)
data class Word(
    @PrimaryKey @ColumnInfo(name = "group_language_id") var group_language_id : Int,
    @ColumnInfo(name = "original") var original : String,
    @ColumnInfo(name = "translation") var translation : String,
    @ColumnInfo(name = "knowledge") var knowledge : Int,
)

data class GroupAndWord(
    @Embedded var group : Group,
    @Relation(
        parentColumn = "group_id",
        entityColumn = "group_language_id"
    )
    var words : List<Word>
)
@Dao
interface GroupDao {
    @Query("SELECT * FROM group_language")
    fun getAll(): List<Group>

    @Query("SELECT * FROM group_language WHERE group_id IN (:groupIds)")
    fun loadAllByIds(groupIds: IntArray): List<Group>

    @Query("SELECT group_id FROM group_language WHERE language1 LIKE :language1 AND language2 LIKE :language2 LIMIT 1")
    fun findByLanguageGroup(language1: String, language2: String) : List<Int>

    @Query("INSERT INTO group_language VALUES (null, :l1, :l2)")
    fun insertLanguageGroup(l1: String, l2: String)
}
@Dao
interface WordDao {
    @Query("SELECT original FROM words WHERE group_language_id=:group_language_id")
    fun getOriginalByLanguageGroupId(group_language_id : Int) : List<String>

    @Query("SELECT translation FROM words WHERE group_language_id=:group_language_id")
    fun getTranslationByLanguageGroupId(group_language_id : Int) : List<String>

    @Query("INSERT INTO words VALUES (:id, :original, :translation, :knowledge)")
    fun insertLanguageGroup(id: Int, original: String, translation: String, knowledge: Int)
}

@Database(entities = [Group::class, Word::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun wordDao(): WordDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "stem.db")
                .build()
        }
    }
}

