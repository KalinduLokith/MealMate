package com.example.mobilecourseworktwo

import java.sql.Connection

object IngredientsForUsers
    {

        fun showPotionsViaTextView(potionsList: List<DataBase>): StringBuilder
        {

            val potionsRecipes = StringBuilder()


            for(meal in potionsList)
            {
                val ingredients = mutableListOf<String>()
                val quantities = mutableListOf<String>()

                for ( i in 1..20 )
                {
                    val ingredient = meal.getIngredient(i)
                    val quantity = meal.getQuantity(i)

                    if (ConfigurationDetails.Configuration(ingredient) && ingredient != null)
                        ingredients.add(ingredient)

                    if(ConfigurationDetails.Configuration(quantity) && quantity != null)
                        quantities.add(quantity)
                }

                with(meal)
                {
                    potionsRecipes.append("Meal:$strMeal\n")
                    potionsRecipes.append("DrinkAlternate:$strDrinkAlternate\n")
                    potionsRecipes.append("Category:$strCategory\n")
                    potionsRecipes.append("Area:$strArea\n")
                    potionsRecipes.append("Instructions:$strInstructions\n")
                    potionsRecipes.append("Tags:$strTags\n")
                    potionsRecipes.append("Youtube:$strYoutube\n")

                    for (i in ingredients.indices)
                    {
                        potionsRecipes.append("Ingredient${i + 1}:${ingredients[i]}\n")
                    }

                    for (i in quantities.indices)
                    {
                        potionsRecipes.append("Measure${i + 1}:${quantities[i]}\n")
                    }
                    potionsRecipes.append("\n\n")
                }
            }
            // return the stringBuilder object.
            return potionsRecipes
        }

        // The showRecipesInRecyclerView function is declared as a suspend function, so it can be called from within a coroutine.
        suspend fun showRecipesInRecyclerView(mealList: List<DataBase>, mealPair: ArrayList<ImageDescriptioPair>): ArrayList<ImageDescriptioPair>
        {
            for (meal in mealList)
            {
                val ingredients = mutableListOf<String>()
                val measurements = mutableListOf<String>()

                for (i in 1..20)
                {
                    val ingredient = meal.getIngredient(i)
                    val measurement = meal.getQuantity(i)

                    if (ConfigurationDetails.Configuration(ingredient) && ingredient != null)
                        ingredients.add(ingredient)

                    if(ConfigurationDetails.Configuration(measurement) && measurement != null)
                        measurements.add(measurement)
                }

                val recipe = StringBuilder()
                with(meal)
                {
                    recipe.append("Meal:$strMeal\n")
                    recipe.append("DrinkAlternate:$strDrinkAlternate\n")
                    recipe.append("Category:$strCategory\n")
                    recipe.append("Area:$strArea\n")
                    recipe.append("Instructions:$strInstructions\n")
                    recipe.append("strMealThumb :- $strMealThumb\n")
                    recipe.append("Tags:$strTags\n")
                    recipe.append("Youtube:$strYoutube\n")

                    for (i in ingredients.indices)
                    {
                        recipe.append("Ingredient${i + 1}:${ingredients[i]}\n")
                    }

                    for (i in measurements.indices)
                    {
                        recipe.append("Measure${i + 1}:${measurements[i]}\n")
                    }
                    recipe.append("\n\n")
                }

                mealPair.add(ImageDescriptioPair( ConnectionTest.loadforImage(meal.strMealThumb!!), recipe.toString()) )
            }
            return mealPair
        }
    }
