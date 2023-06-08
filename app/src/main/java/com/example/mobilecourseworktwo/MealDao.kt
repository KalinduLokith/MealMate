package com.example.mobilecourseworktwo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MealDao {
    @Query("Select * from DataBase ")
    suspend fun getAll():List<DataBase>
    @Query("SELECT * FROM DataBase WHERE strMeal LIKE '%' || :query || '%' " +
            "OR strIngredient1 LIKE '%' || :query || '%' " +
            "OR strIngredient2 LIKE '%' || :query || '%' " +
            "OR strIngredient3 LIKE '%' || :query || '%' " +
            "OR strIngredient4 LIKE '%' || :query || '%' " +
            "OR strIngredient5 LIKE '%' || :query || '%' " +
            "OR strIngredient6 LIKE '%' || :query || '%' " +
            "OR strIngredient7 LIKE '%' || :query || '%' " +
            "OR strIngredient8 LIKE '%' || :query || '%' " +
            "OR strIngredient9 LIKE '%' || :query || '%' " +
            "OR strIngredient10 LIKE '%' || :query || '%' " +
            "OR strIngredient11 LIKE '%' || :query || '%' " +
            "OR strIngredient12 LIKE '%' || :query || '%' " +
            "OR strIngredient13 LIKE '%' || :query || '%' " +
            "OR strIngredient14 LIKE '%' || :query || '%' " +
            "OR strIngredient15 LIKE '%' || :query || '%' " +
            "OR strIngredient16 LIKE '%' || :query || '%' " +
            "OR strIngredient17 LIKE '%' || :query || '%' " +
            "OR strIngredient18 LIKE '%' || :query || '%' " +
            "OR strIngredient19 LIKE '%' || :query || '%' " +
            "OR strIngredient20 LIKE '%' || :query || '%' " )

    fun nameForPotionOrIngredient(query: String): List<DataBase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(vararg meals: DataBase)

    @Insert
    suspend fun insertAll(vararg meals:DataBase)

}