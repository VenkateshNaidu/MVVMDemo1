package com.venkygithub.mvvmdemo1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.venkygithub.mvvmdemo1.data.db.entities.CURRENT_USER_ID
import com.venkygithub.mvvmdemo1.data.db.entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("SELECT * FROM user_table WHERE uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>

}