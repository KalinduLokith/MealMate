package com.example.mobilecourseworktwo

import android.content.Context
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Database
import androidx.room.Room
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL


var apiKeyNumber =1

class IngredientSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_search)

        var searchIngredientText = findViewById<EditText>(R.id.EditText)
        var retriewMealButton = findViewById<Button>(R.id.retrieveBtn)
        var saveMealsToDataBaseButton = findViewById<Button>(R.id.saveBtn)
        var textViewMeals = findViewById<TextView>(R.id.textView)

        retriewMealButton.setOnClickListener {


            var searchIngredients = searchIngredientText.text.toString()

            runBlocking {
                launch {
                    withContext(Dispatchers.IO) {
                        val potionlist: List<DataBase> =
                            editTextPotionRecipeOfIngredient(searchIngredients)

                        val stringBuilderPotionRecipes: StringBuilder =
                            showDetailsOfPotions(potionlist)

                        runOnUiThread {
                            if (stringBuilderPotionRecipes.isNotEmpty()) {
                                textViewMeals.text = stringBuilderPotionRecipes.toString()
                            } else {
                                textViewMeals.text = "Cannot find any potion for related"
                            }
                        }
                    }
                }
            }


        }
        saveMealsToDataBaseButton.setOnClickListener {
            val searchIngredient = searchIngredientText.text.toString()

            runBlocking {
                launch {
                    withContext(Dispatchers.IO) {
                        val DataBase: List<DataBase> =
                            editTextPotionRecipeOfIngredient(searchIngredient)
                        saveMealsToDatabase(DataBase)
                    }
                }
            }

        }
    }


    private fun editTextPotionRecipeOfIngredient(ingredients: String): List<DataBase> {

        val urlSummary =
            URL("https://www.themealdb.com/api/json/v1/$apiKeyNumber/filter.php?i=$ingredients")

        //retrieving the return value from api
        val jsonStringOutSummery = urlSummary.readText()
//            Log.d(
//                "#################################"
//                        jsonStringOutSummery
//            )
        //convert output to the Json objects
        val summeryInJsonForm = JSONObject(jsonStringOutSummery)

        //get json array called meals
        val mealsSummary = summeryInJsonForm.getJSONArray("meals")
        //create a mutable list object to hold meal instances
        val detailsOfRecipe = mutableListOf<DataBase>()


        for (i in 0 until mealsSummary.length()) {
            val detailsOfTheMeal = mealsSummary.getJSONObject(i)
            //get meal id
            val idMeal = detailsOfTheMeal.getInt("idMeal")
            var url =
                URL("https://www.themealdb.com/api/json/v1/$apiKeyNumber/lookup.php?i=$idMeal")
            val jsonStrings = url.readText()
            //convert output to the json objects
            val jsonOb = JSONObject(jsonStrings)

            //get Json array called meals
            val meals = jsonOb.getJSONArray("meals")

            for (i in 0 until meals.length()) {
                //get meals by meal
                val meal = meals.getJSONObject(i)

                val strMeal = meal.getString("strMeal")
                val strDinkAlternate = meal.getString("strDrinkAlternate")
                val strCategory = meal.getString("strCategory")
                val strArea = meal.getString("strArea")
                val strInstructions = meal.getString("strInstructions")
                val strMealThumb = meal.getString("strMealThumb")
                val strTags = meal.getString("strTags")
                val strYoutube = meal.getString("strYoutube")
                val strIngredient1 = meal.getString("strIngredient1")
                val strIngredient2 = meal.getString("strIngredient2")
                val strIngredient3 = meal.getString("strIngredient3")
                val strIngredient4 = meal.getString("strIngredient4")
                val strIngredient5 = meal.getString("strIngredient5")
                val strIngredient6 = meal.getString("strIngredient6")
                val strIngredient7 = meal.getString("strIngredient7")
                val strIngredient8 = meal.getString("strIngredient8")
                val strIngredient9 = meal.getString("strIngredient9")
                val strIngredient10 = meal.getString("strIngredient10")
                val strIngredient11 = meal.getString("strIngredient11")
                val strIngredient12 = meal.getString("strIngredient12")
                val strIngredient13 = meal.getString("strIngredient13")
                val strIngredient14 = meal.getString("strIngredient14")
                val strIngredient15 = meal.getString("strIngredient15")
                val strIngredient16 = meal.getString("strIngredient16")
                val strIngredient17 = meal.getString("strIngredient17")
                val strIngredient18 = meal.getString("strIngredient18")
                val strIngredient19 = meal.getString("strIngredient19")
                val strIngredient20 = meal.getString("strIngredient20")
                val strMeasure1 = meal.getString("strMeasure1")
                val strMeasure2 = meal.getString("strMeasure2")
                val strMeasure3 = meal.getString("strMeasure3")
                val strMeasure4 = meal.getString("strMeasure4")
                val strMeasure5 = meal.getString("strMeasure5")
                val strMeasure6 = meal.getString("strMeasure6")
                val strMeasure7 = meal.getString("strMeasure7")
                val strMeasure8 = meal.getString("strMeasure8")
                val strMeasure9 = meal.getString("strMeasure9")
                val strMeasure10 = meal.getString("strMeasure10")
                val strMeasure11 = meal.getString("strMeasure11")
                val strMeasure12 = meal.getString("strMeasure12")
                val strMeasure13 = meal.getString("strMeasure13")
                val strMeasure14 = meal.getString("strMeasure14")
                val strMeasure15 = meal.getString("strMeasure15")
                val strMeasure16 = meal.getString("strMeasure16")
                val strMeasure17 = meal.getString("strMeasure17")
                val strMeasure18 = meal.getString("strMeasure18")
                val strMeasure19 = meal.getString("strMeasure19")
                val strMeasure20 = meal.getString("strMeasure20")
                val strSource = meal.getString("strSource")
                val strImageSource = meal.getString("strImageSource")
                val strCreativeCommonsConfirmed = meal.getString("strCreativeCommonsConfirmed")
                val dateModified = meal.getString("dateModified")

                detailsOfRecipe.add(
                    DataBase(
                        idMeal,
                        strMeal,
                        strDinkAlternate,
                        strCategory,
                        strArea,
                        strInstructions,
                        strMealThumb,
                        strTags,
                        strYoutube,
                        strIngredient1,
                        strIngredient2,
                        strIngredient3,
                        strIngredient4,
                        strIngredient5,
                        strIngredient6,
                        strIngredient7,
                        strIngredient8,
                        strIngredient9,
                        strIngredient10,
                        strIngredient11,
                        strIngredient12,
                        strIngredient13,
                        strIngredient14,
                        strIngredient15,
                        strIngredient16,
                        strIngredient17,
                        strIngredient18,
                        strIngredient19,
                        strIngredient20,
                        strMeasure1,
                        strMeasure2,
                        strMeasure3,
                        strMeasure4,
                        strMeasure5,
                        strMeasure6,
                        strMeasure7,
                        strMeasure8,
                        strMeasure9,
                        strMeasure10,
                        strMeasure11,
                        strMeasure12,
                        strMeasure13,
                        strMeasure14,
                        strMeasure15,
                        strMeasure16,
                        strMeasure17,
                        strMeasure18,
                        strMeasure19,
                        strMeasure20,
                        strSource,
                        strImageSource,
                        strCreativeCommonsConfirmed,
                        dateModified
                    )
                )
            }
        }
        return detailsOfRecipe
    }

    //this methods use for display recipes on textview
    private fun showDetailsOfPotions(mealLists: List<DataBase>): java.lang.StringBuilder {
        //use stringBuilder Object to hold recipeDetails
        val stringBuilderRecipeDetails = StringBuilder()
        for (meals in mealLists) {
            stringBuilderRecipeDetails.append("Meal :- ${meals.strMeal}\n")
            stringBuilderRecipeDetails.append("DrinkAlternate :- ${meals.strDrinkAlternate}\n")
            stringBuilderRecipeDetails.append("Category :- ${meals.strCategory}\n")
            stringBuilderRecipeDetails.append("Area :- ${meals.strArea}\n")
            stringBuilderRecipeDetails.append("Instructions :- ${meals.strInstructions}\n")
            stringBuilderRecipeDetails.append("strMealThumb :- ${meals.strMealThumb}\n")
            stringBuilderRecipeDetails.append("Tags :- ${meals.strMealThumb}\n")
            stringBuilderRecipeDetails.append("Youtube :- ${meals.strYoutube}\n")
            if (validateNullAndBlanks(meals.strIngredient1)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient1}\n")
            if (validateNullAndBlanks(meals.strIngredient2)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient2}\n")
            if (validateNullAndBlanks(meals.strIngredient3)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient3}\n")
            if (validateNullAndBlanks(meals.strIngredient4)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient4}\n")
            if (validateNullAndBlanks(meals.strIngredient5)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient5}\n")
            if (validateNullAndBlanks(meals.strIngredient6)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient6}\n")
            if (validateNullAndBlanks(meals.strIngredient4)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient4}\n")
            if (validateNullAndBlanks(meals.strIngredient5)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient5}\n")
            if (validateNullAndBlanks(meals.strIngredient6)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient6}\n")
            if (validateNullAndBlanks(meals.strIngredient7)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient7}\n")
            if (validateNullAndBlanks(meals.strIngredient8)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient8}\n")
            if (validateNullAndBlanks(meals.strIngredient9)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient9}\n")
            if (validateNullAndBlanks(meals.strIngredient10)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient10}\n")
            if (validateNullAndBlanks(meals.strIngredient11)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient11}\n")
            if (validateNullAndBlanks(meals.strIngredient12)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient12}\n")
            if (validateNullAndBlanks(meals.strIngredient13)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient13}\n")
            if (validateNullAndBlanks(meals.strIngredient14)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient14}\n")
            if (validateNullAndBlanks(meals.strIngredient15)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient15}\n")
            if (validateNullAndBlanks(meals.strIngredient16)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient16}\n")
            if (validateNullAndBlanks(meals.strIngredient17)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient7}\n")
            if (validateNullAndBlanks(meals.strIngredient18)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient8}\n")
            if (validateNullAndBlanks(meals.strIngredient19)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient19}\n")
            if (validateNullAndBlanks(meals.strIngredient20)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strIngredient20}\n")
            if (validateNullAndBlanks(meals.strMeasure1)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure1}\n")
            if (validateNullAndBlanks(meals.strMeasure2)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure2}\n")
            if (validateNullAndBlanks(meals.strMeasure3)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure3}\n")
            if (validateNullAndBlanks(meals.strMeasure4)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure4}\n")
            if (validateNullAndBlanks(meals.strMeasure5)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure5}\n")
            if (validateNullAndBlanks(meals.strMeasure6)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure6}\n")
            if (validateNullAndBlanks(meals.strMeasure7)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure7}\n")
            if (validateNullAndBlanks(meals.strMeasure8)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure8}\n")
            if (validateNullAndBlanks(meals.strMeasure9)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure9}\n")
            if (validateNullAndBlanks(meals.strMeasure10)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure10}\n")
            if (validateNullAndBlanks(meals.strMeasure11)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure11}\n")
            if (validateNullAndBlanks(meals.strMeasure12)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure12}\n")
            if (validateNullAndBlanks(meals.strMeasure13)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure13}\n")
            if (validateNullAndBlanks(meals.strMeasure14)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure14}\n")
            if (validateNullAndBlanks(meals.strMeasure15)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure15}\n")
            if (validateNullAndBlanks(meals.strMeasure16)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure16}\n")
            if (validateNullAndBlanks(meals.strMeasure17)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure17}\n")
            if (validateNullAndBlanks(meals.strMeasure18)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure18}\n")
            if (validateNullAndBlanks(meals.strMeasure19)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure19}\n")
            if (validateNullAndBlanks(meals.strMeasure20)) stringBuilderRecipeDetails.append("Ingredient1 :- ${meals.strMeasure20}\n")
            stringBuilderRecipeDetails.append("\n\n")


        }
        return stringBuilderRecipeDetails
    }

    private fun validateNullAndBlanks(str: String?): Boolean {
        return if (str == "null")
            false
        else !(str == "" || str == " ")
    }

    private suspend fun saveMealsToDatabase(mealList: List<DataBase>) {
        try {
            val databaseID = intent.getStringExtra("dataCollect")
            var database = Room.databaseBuilder(this, DBConnection::class.java, databaseID).build()
            val mealDao = database.databaseDao()
            for (i in mealList) {
                mealDao.insertMeals(i)
            }

        } catch (e: java.lang.Exception) {


        }
    }
}

