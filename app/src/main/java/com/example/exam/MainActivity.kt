package com.example.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exam.ui.theme.ExamTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("EventEase", style = MaterialTheme.typography.titleLarge) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    },
                    bottomBar = {
                        BottomButtons()
                    }
                ) { innerPadding ->
                    EventDetailScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LightThemePreview() {
    ExamTheme(darkTheme = false) {
        EventDetailScreen(Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
fun DarkThemePreview() {
    ExamTheme(darkTheme = true) {
        EventDetailScreen(Modifier.fillMaxWidth())
    }
}


@Composable
fun EventDetailScreen(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding( top = 16.dp,
                bottom = 80.dp) // Padding to avoid overlapping with bottom bar
    ) {
        item { EventHeaderSection() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { EventScheduleSection() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { ReviewsSection() }
    }
}

@Composable
fun EventHeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.event), // Replace with actual image resource
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Tech Conference 2024",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Mehsana, Gujarat | 2.5 km away",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "This is a detailed description of the event...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}


// Event data structure with name and time
data class Event(val name: String, val time: String, val description: String)

@Composable
fun EventScheduleSection() {
    // Define the event list with names, timings, and descriptions
    val events = listOf(
        Event("Opening Ceremony", "9:00 AM", "Kickoff the event with a welcome message and introduction."),
        Event("Keynote Speech", "10:00 AM", "A renowned speaker will deliver a keynote address."),
        Event("Networking Session", "12:00 PM", "An opportunity to network with industry professionals.")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Event Schedule",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))

        // LazyRow to display events horizontally
        LazyRow {
            items(events) { event ->
                ScheduleCard(event)
            }
        }
    }
}


@Composable
fun ScheduleCard(event: Event) {
    Card(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(250.dp)
            .height(160.dp),  // Increased height to accommodate description
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(8.dp)
        ) {
            Column {
                // Event Time - Positioned above the event name
                Text(
                    text = event.time,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Event Name - Positioned below the time
                Text(
                    text = event.name,
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 35.sp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Event Description - Positioned below the name
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}




@Composable
fun ReviewsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Reviews",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            val reviews = listOf(
                Review("Alice Johnson", "Great event! Well-organized and informative.", 5),
                Review("Bob Smith", "Really enjoyed the keynote speaker. Would recommend!", 4),
                Review("Charlie Davis", "Good overall, but some sessions were too short.", 3)
            )
            reviews.forEach { review ->
                ReviewCard(review)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ReviewCard(review: Review) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.user), // Replace with actual avatar resource
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = review.name, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
                Text(
                    text = review.comment,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Text(
                    text = "★".repeat(review.rating),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFFFD700) // Gold color for stars
                )
            }
        }
    }
}


@Composable
fun BottomButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { /* Handle Buy Tickets */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Buy Tickets", color = MaterialTheme.colorScheme.onPrimary)
        }
        Button(
            onClick = { /* Handle Add to Calendar */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(text = "Add to Calendar", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}


data class Review(val name: String, val comment: String, val rating: Int)
