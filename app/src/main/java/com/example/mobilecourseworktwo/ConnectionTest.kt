package com.example.mobilecourseworktwo


    import android.content.Context
    import android.graphics.Bitmap
    import android.graphics.BitmapFactory
    import android.net.ConnectivityManager
    import android.net.NetworkCapabilities
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.withContext
    import java.net.HttpURLConnection
    import java.net.URL

    object ConnectionTest
    {

        fun checkConnectionAvailability(context: Context): Boolean
        {
            val userConnectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkConnection = userConnectionManager.activeNetwork
            val networkAvailability = userConnectionManager.getNetworkCapabilities(networkConnection)
            return if ( networkAvailability != null && networkAvailability.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET) )
            {
                true
            }
            else
            {
                DetailsForUser.DisplayError("No Internet Connection", "Please check your internet connection and try again.", "Network Connectivity Failure", context)
                false
            }
        }

        suspend fun loadforImage(url: String): Bitmap
        {
            val currentUrl =
                "https://www.refrigeratedfrozenfood.com/ext/resources/NEW_RD_Website/DefaultImages/default-pasta.jpg?1430942591"
            return withContext(Dispatchers.IO)
            {
                try
                {
                    // Download the image from the URL
                    val connections = URL(url).openConnection() as HttpURLConnection
                    connections.doInput = true

                    connections.connect()
                    val inptStream = connections.inputStream

                    // Decode the input stream into a bitmap
                    BitmapFactory.decodeStream(inptStream)
                }
                catch (e: Exception)
                {
                    e.printStackTrace()
                    // If the image is not available, return the default bitmap
                    val currentConnection =
                        URL(currentUrl).openConnection() as HttpURLConnection
                    currentConnection.doInput = true
                    currentConnection.connect()
                    val defaultInputStream = currentConnection.inputStream
                    BitmapFactory.decodeStream(defaultInputStream)
                }
            }
        }
    }
