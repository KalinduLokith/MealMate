package com.example.mobilecourseworktwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.*

class SearchByMeals : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mealPaired: ArrayList<ImageDescriptioPair>
    private val MealCategory: MealCategory by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_by_meals)

        val editTextField = findViewById<EditText>(R.id.search_meal_textBox)
        val searchingButton = findViewById<Button>(R.id.search_meal_button)
       recyclerView = findViewById(R.id.recyclerview_one)
        recyclerView.layoutManager = LinearLayoutManager(this@SearchByMeals)
        mealPaired = MealCategory.mealPair?: ArrayList()

        recyclerView.adapter = ImageDescriptionAdapterDetails(mealPaired)

        searchingButton.setOnClickListener{
            // get the user input
            val getUserInput = editTextField.text.toString()
            // Validate user Input, empty user inputs will fail the validation
            if (ConfigurationDetails.InputValidation(getUserInput, this)){
                // Can't therefrom database in/out operations in main thread
                //start a new coroutine and run the db operation there
                //connect the main code with a new coroutine scope
                //runBlocking block of code
                runBlocking {

                    launch {

                        withContext(Dispatchers.IO) {
                            try {
                                val DataBaseID = intent.getStringExtra("db_id")
                                val DataBase = Room.databaseBuilder(this@SearchByMeals,
                                    DBConnection::class.java,DataBaseID).build()




                                val potionDao =DataBase.databaseDao()

                                val potions=potionDao.nameForPotionOrIngredient(getUserInput)

//                                val stringBuilderRecipientMeal : StringBuilder =
//                                    ShowRecipeForUser.displayRecipeOnTextView(potions)

                                runOnUiThread{
                                    GlobalScope.launch (Dispatchers.Main){
                                        val listOfPotions: List<DataBase> = potions
                                        mealPaired = withContext(Dispatchers.Default){
                                            IngredientsForUsers.showRecipesInRecyclerView(listOfPotions,mealPaired)
                                       }
                                       val detailsOfAdapter = recyclerView.adapter as ImageDescriptionAdapterDetails
                                        detailsOfAdapter.notifyDataSetChanged()

                                    }
                                }

                            } catch (e:Exception) {
                                DetailsForUser.DisplayError(
                                    "Error",
                                    "Error occurred while retrieving data from the DataBase ",
                                    e.message.toString(),
                                    this@SearchByMeals
                                )

                            }
                        }
                    }
                }

            }
        }

    }
}