package com.dark.project_widget_hehe.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.dark.project_widget_hehe.R
import kotlin.random.Random

// Data model for a Quote.
data class Quote(val text: String, val author: String)

class QuoteAppWidget : AppWidgetProvider() {

    companion object {
        // Predefined list of at least 20 quotes.
        private val quotes = listOf(
            Quote("The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt"),
            Quote("In the middle of difficulty lies opportunity.", "Albert Einstein"),
            Quote("Life is 10% what happens to us and 90% how we react to it.", "Charles R. Swindoll"),
            Quote("The best way to predict your future is to create it.", "Abraham Lincoln"),
            Quote("You miss 100% of the shots you don't take.", "Wayne Gretzky"),
            Quote("Whether you think you can or you think you can't, you're right.", "Henry Ford"),
            Quote("The mind is everything. What you think you become.", "Buddha"),
            Quote("Your time is limited, don't waste it living someone else's life.", "Steve Jobs"),
            Quote("I have not failed. I've just found 10,000 ways that won't work.", "Thomas Edison"),
            Quote("Strive not to be a success, but rather to be of value.", "Albert Einstein"),
            Quote("The only way to do great work is to love what you do.", "Steve Jobs"),
            Quote("If you can dream it, you can achieve it.", "Zig Ziglar"),
            Quote("Believe you can and you're halfway there.", "Theodore Roosevelt"),
            Quote("Change your thoughts and you change your world.", "Norman Vincent Peale"),
            Quote("The purpose of our lives is to be happy.", "Dalai Lama"),
            Quote("Don't watch the clock; do what it does. Keep going.", "Sam Levenson"),
            Quote("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
            Quote("It does not matter how slowly you go as long as you do not stop.", "Confucius"),
            Quote("The harder you work for something, the greater you'll feel when you achieve it.", "Unknown"),
            Quote("Don't stop when you're tired. Stop when you're done.", "Unknown")
        )

        // Function to get a random quote.
        fun getRandomQuote() = quotes.random()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // Update all active widgets
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        // Check for our custom update action triggered on tap.
        if (intent.action == "com.dark.project_widget_hehe.UPDATE_QUOTE") {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val componentName = ComponentName(context, QuoteAppWidget::class.java)
            val widgetIds = appWidgetManager.getAppWidgetIds(componentName)
            for (appWidgetId in widgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Select a random quote.
    val quote = QuoteAppWidget.getRandomQuote()

    // Inflate the widget layout.
    val views = RemoteViews(context.packageName, R.layout.quote_app_widget)

    // Set the quote text and author in the TextViews.
    views.setTextViewText(R.id.quote_text, "\"${quote.text}\"")
    views.setTextViewText(R.id.quote_author, "- ${quote.author}")

    // Set up the tap action: when the widget is tapped, trigger a broadcast to update the quote.
    val intent = Intent(context, QuoteAppWidget::class.java).apply {
        action = "com.dark.project_widget_hehe.UPDATE_QUOTE"
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    // Assign the pending intent to the widget container so that a tap anywhere triggers the update.
    views.setOnClickPendingIntent(R.id.widget_container, pendingIntent)

    // Instruct the widget manager to update the widget.
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
