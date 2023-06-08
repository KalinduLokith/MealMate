package com.example.mobilecourseworktwo

import android.content.Context

    object ConfigurationDetails {

        fun Configuration(str:String?):Boolean {
            return if (str == "null")
                false
            else (str == "" || str == " ")
        }

        fun InputValidation(userInputs:String, context: Context):Boolean {
            return if (userInputs != "") {
                true
            } else {
                DetailsForUser.DisplayError("Enter an Ingredient",
                    "Pleace Enter an Ingredient Before Retrieve Meals.",
                    "Empty Ingredient is not valid", context)
                false
            }
        }
    }

