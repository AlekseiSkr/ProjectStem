//package com.example.projectstem
//
//import android.content.Context
//import androidx.room.Room
//import com.example.projectstem.model.AppDatabase
//import com.example.projectstem.model.GroupDao
//import org.junit.Test
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import com.example.projectstem.model.Group
//import org.junit.After
//import org.junit.Assert;
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.runner.RunWith
//import org.junit.runner.manipulation.Ordering
//import org.robolectric.RobolectricTestRunner
//import java.io.IOException
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//
//@RunWith(RobolectricTestRunner::class)
//class DbModel {
//    class SimpleEntityReadWriteTest {
//        private lateinit var groupDao: GroupDao
//        private lateinit var db: AppDatabase
//
//        @Before
//        fun createDb() {
//            val context = ApplicationProvider.getApplicationContext<Context>()
//            db = Room.inMemoryDatabaseBuilder(
//                context, AppDatabase::class.java
//            ).build()
//
//        }
//        @After
//        @Throws(IOException::class)
//        fun closeDb() {
//            db.close()
//        }
//
//        @Test
//        @Throws(Exception::class)
//        fun writeUserAndReadInList() {
//            db.groupDao().insertLanguageGroup("English", "Spanish")
//            val groupLanguage = groupDao.findByLanguageGroup("English", "Spanish")
//            assertEquals(groupLanguage, "1")
//        }
//
//    }
//}