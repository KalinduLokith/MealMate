# MealMate
This developed application will be helping users with meal preparation

implement an Android application using Kotlin that assists users with meal preparation. The application will utilize the "https://www.themealdb.com/api.php/" web service and the Room Library for managing meal information. The specifications for the application are as follows:

    1.Application Start:

          Upon launching the application, the user will be presented with three buttons: "Add Meals to DB," "Search for Meals By Ingredient," and "Search for Meals."
     
    2.Add Meals to DB:

          Clicking on the "Add Meals to DB" button will save the details of a few meals in a local SQLite database using the Room library.
          The meal information can be hardcoded within the application.
          You need to create an appropriate database and populate it with the provided meal data.
          This task is divided into two parts: creating the database (10 marks) and populating it with data (10 marks).
    
    3.Search for Meals By Ingredient:

          This feature involves using the "https://www.themealdb.com/api.php/" web service.
          Upon clicking the "Search for Meals By Ingredient" button, the user will be presented with a screen containing a single textbox and two buttons: "Retrieve Meals" and "Save meals to Database."
          The user will enter an ingredient name in the textbox and click "Retrieve Meals" to fetch the details of all meals containing that ingredient from the web service.
          The retrieved meal details will be displayed on the same screen.
          The "Save meals to Database" button allows the user to save the retrieved meal details to the local SQLite database using the same tables created earlier.
    
    4.Search for Meals:

          Clicking on the "Search for Meals" button will display a screen with a textbox and a search button.
          The user can enter a string in the textbox, which will be used to search for meals in the database that match either the meal name or ingredients (partial match is allowed).
          The search should be case-insensitive.
          All matching meals will be displayed to the user.
          
    5.Display Meal Images:

          Extend the application so that when the user searches for meals in the previous step, the displayed meals will also show their thumbnail images.

    6.Device Rotation Handling:

          The application should handle device rotation (from portrait to landscape and vice versa) seamlessly.
          When the device is rotated, the application should preserve the current screen and data, ensuring the user continues from the same point.
    
    7.Additional Search Feature:

          Enhance the initial screen by adding an extra button that allows the user to search for meals directly from the web service.
          The user can enter a string, and all meals containing that substring in their names will be retrieved and displayed.
