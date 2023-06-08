package com.example.mobilecourseworktwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class SearchMealByWeb : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pairs: ArrayList<ImageDescriptioPair>
    private val viewModel: MealCategory by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meal_by_web)


        // get text fields and buttons as kotlin objects.
        val txtSearch = findViewById<EditText>(R.id.set_text)
        val btnSearch = findViewById<Button>(R.id.set_button)

        // get recyclerView as an object
        recyclerView = findViewById(R.id.recycler_view)

        // restore previous data.

        // set recycler view layout manager
        recyclerView.layoutManager = LinearLayoutManager(this@SearchMealByWeb)

        pairs = viewModel.mealPair ?: ArrayList<ImageDescriptioPair>()
        // set recycler view adapter
        recyclerView.adapter = ImageDescriptionAdapterDetails(pairs)

        btnSearch.setOnClickListener {
            // checking whether the network connection is available or not.
            if ( ConnectionTest.checkConnectionAvailability(this) )
            {
                // get the user's input.
                val usrInput = txtSearch.text.toString()

                // validate the user input. empty user inputs will fail the validation.
                if ( ConfigurationDetails.InputValidation(usrInput, this) )
                {
                    runOnUiThread{
                        // runOnUiThread block is modified to launch a coroutine that runs on the UI
                        // thread using the GlobalScope.launch(Dispatchers.Main) function. The
                        // withContext(Dispatchers.Default) function specifies that the showRecipesInRecyclerView
                        // function should run on a separate thread to avoid blocking the UI thread.
                        GlobalScope.launch(Dispatchers.Main) {
                            val mealList: List<DataBase> = searchMealsByName(usrInput)

                            recyclerView.layoutManager =
                                LinearLayoutManager(this@SearchMealByWeb)

                            // Clear the pairs list before adding new data to it
                            pairs.clear()

                            //  The withContext function is used to run the showRecipesInRecyclerView
                            //  function on the default dispatcher, which is a separate thread. The
                            //  result of the showRecipesInRecyclerView function is returned to the
                            //  pairs variable, which is then used to create the adapter for the RecyclerView.
                            pairs = withContext(Dispatchers.Default) {
                                IngredientsForUsers.showRecipesInRecyclerView(mealList, pairs)
                            }

                            // creating adapter for recyclerView.
                            val adapter = ImageDescriptionAdapterDetails(pairs)
                            // setting adapter for recyclerView.
                            recyclerView.adapter = adapter
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }

    /**
     * override onSaveInstanceState method.
     */
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        viewModel.mealPair = pairs
    }

    /**
     * this method will search the recipes related to the given ingredient and add them to a list.
     * @param mealName the name of the meal, that need to search.
     */
    private fun searchMealsByName(mealName: String): List<DataBase>
    {
        // create a mutable list object which can hold Meal instances.
        var recipes = mutableListOf<DataBase>()

        // using a try-catch block, because errors can be thrown in this particular block of code.
        try {
            // can't perform network operations on the main thread.
            // so starting a new coroutine and run the network operation there.
            // Connect the main code with a new coroutine scope using a
            // runBlocking block of code.
            runBlocking {
                //  Start a new coroutine with a launch block of code.
                launch {
                    // Run the code of the coroutine in a new thread different than the main.
                    withContext(Dispatchers.IO)
                    {
                        val url = URL("https://www.themealdb.com/api/json/v1/1/search.php?s=$mealName")
                        val conn = url.openConnection() as HttpURLConnection
                        conn.requestMethod = "GET"

                        val response = StringBuilder()
                        Scanner(conn.inputStream).use { scanner ->
                            while (scanner.hasNextLine()) {
                                response.append(scanner.nextLine())
                            }
                        }

                        val json = JSONObject(response.toString())
                        val jsonArray = json.getJSONArray("meals")
                        for (i in 0 until jsonArray.length()) {
                            val mealJson = jsonArray.getJSONObject(i)

                            // getting all data and adding all retrieved Meal data to mutable list.
                            recipes = DataReceiver.getJsonData(mealJson, recipes)
                        }
                    }
                }
            }
        }
        catch (e: Exception)
        {
            // this method ( searchMealsByName() ) is invoked inside a coroutine(another lightweight
            // thread) to allow it to use the network.
            // so we need to switch to ui(main) thread before show an error message Dialog.
            runOnUiThread {
                // if an error occurred, show error message inside a alert dialog box.
                DetailsForUser.DisplayError(
                    "Error",
                    "An Error Occurred While Retrieving the Data. ",
                    e.message.toString(),
                    this
                )
            }
        }
        // returning recipes mutable list.
        return recipes
    }
}

