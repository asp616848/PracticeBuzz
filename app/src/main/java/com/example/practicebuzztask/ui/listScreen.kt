import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practicebuzztask.item.listItem

@Composable
fun listScreen(navController: NavController){
    // hardcoding the list
    val item1= listItem("Title", "Description")
    val list = remember { mutableStateListOf<listItem>(item1)}

    Column{
        Spacer(Modifier.height(30.dp))
        Box(modifier = Modifier.height(650.dp)
            .padding(10.dp)
            .border(2.dp, Color.Blue, shape = RoundedCornerShape(10.dp))){
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(list.size) { product ->
                    itemComposable(list[product])
                }
            }
        }

        // create the item here for now, time constraints
        Box(modifier = Modifier
            .padding(10.dp)
            .border(2.dp, Color.Blue, shape = RoundedCornerShape(10.dp))) {

            var title = remember { mutableStateOf("") }
            var desc = remember { mutableStateOf("") }
            val context = LocalContext.current;

            Column(modifier = Modifier.fillMaxWidth()){
                TextField(
                    value = title.value,
                    onValueChange = { title.value = it.toString() },
                    Modifier.fillMaxWidth()
                )
                TextField(
                    value = desc.value,
                    onValueChange = { desc.value = it.toString() } ,
                    Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { list.add(listItem(title.value, desc.value))
                    Toast.makeText(context, "item created: ${title.value} , ${desc.value}", Toast.LENGTH_SHORT).show()}) {
                    Text("Save this item")
                }
            }
        }
    }
}

@Composable
fun itemComposable(item: listItem){
    val context = LocalContext.current
    Box(modifier = Modifier
        .padding(10.dp)
        .border(2.dp, Color.Blue, shape = RoundedCornerShape(10.dp))){

        Button(onClick = {itemClicked(item,context)}, modifier = Modifier.fillMaxWidth()){
            Text(item.itemName)
            Spacer(modifier = Modifier.width(10.dp))
            Text(":")
            Spacer(modifier = Modifier.width(10.dp))
            Text(item.itemDescription)
        }
    }
}

fun itemClicked(item: listItem, context: Context) {
    Toast.makeText(context, "${item.itemName}   :    ${item.itemDescription} \n        Clicked", Toast.LENGTH_SHORT ).show()
}
