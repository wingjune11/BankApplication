package com.example.bankapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bankapplication.ui.theme.BankApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(Account("user","admin") )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode")
@Composable
fun GreetingPreview() {
    BankApplicationTheme {
        Surface {
            Greeting(Account("user", "admin"))
        }
    }
}

data class Account(val user: String, val admin: String)
data class Message(val author: String, val body: String)

@Composable
fun Greeting(account: Account, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(all = 50.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(50.dp))
                .border(1.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(50.dp))
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Please select your option:",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium
        )
        Row(modifier = Modifier.padding(all = 50.dp)) {
            Surface(shape = MaterialTheme.shapes.extraSmall, shadowElevation = 1.dp) {
                Text(
                    text = account.user,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Surface(shape = MaterialTheme.shapes.extraSmall, shadowElevation = 1.dp) {
                Text(
                    text = account.admin,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        MessageCard(msg = Message(author = "juan", body = "here I am, where are you?"))
        Conversation(SampleData.conversationSample)
    }
}


@Composable
fun MessageCard(msg: Message){
    Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
        var isExpanded by remember {
            mutableStateOf(false)
        }
        Text(
            text = msg.body,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = if   (isExpanded) Int.MAX_VALUE else 1,
            modifier = Modifier
                .padding(all = 4. dp)
                .clickable { isExpanded = !isExpanded }
        )
    }
}

@Composable
fun Conversation(msgs: List<Message>){
    LazyColumn {
        items(msgs) { msg ->
            MessageCard(msg)
        }
    }
}

@Composable
fun PreviewConversation() {
    BankApplicationTheme {
        Surface {
            Conversation(SampleData.conversationSample)
        }
    }
}
