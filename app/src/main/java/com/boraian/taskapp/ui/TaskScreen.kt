import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.boraian.taskapp.TaskViewModel
import com.boraian.taskapp.data.Todo

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val todos by viewModel.getAllTodos.collectAsState(initial = emptyList())
    var taskInput by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input Field for Task Title
        TextField(
            value = taskInput,
            onValueChange = {
                taskInput = it
                viewModel.updateTask(it)
            },
            label = { Text("Task Title") },
            modifier = Modifier.fillMaxWidth()
        )

        // Add / Update Button
        Button(
            onClick = {
                if (taskInput.isNotEmpty()) {
                    viewModel.insertTodo(viewModel.todo)
                    taskInput = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add / Update Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of Todos with Edit and Favourite functionality
        TodoList(
            todos = todos,
            onDelete = { viewModel.deleteTodo(it) },
            onEdit = {
                viewModel.getTodoById(it.id)
                taskInput = it.task // Update UI with the selected task
            },
            onFavouriteToggle = {
                viewModel.updateFavourite(it.isFavourite.not()) // Toggle the current favourite status
                viewModel.updateTodo(it.copy(isFavourite = !it.isFavourite)) // Save the change in the repository
            }
        )
    }
}

@Composable
fun TodoList(
    todos: List<Todo>,
    onDelete: (Todo) -> Unit,
    onEdit: (Todo) -> Unit,
    onFavouriteToggle: (Todo) -> Unit // Callback to toggle favourite
) {
    LazyColumn {
        items(todos) { todo ->
            TodoItem(
                todo = todo,
                onDelete = onDelete,
                onEdit = onEdit,
                onFavouriteToggle = onFavouriteToggle // Pass favourite toggle action
            )
        }
    }
}

@Composable
fun TodoItem(
    todo: Todo,
    onDelete: (Todo) -> Unit,
    onEdit: (Todo) -> Unit,
    onFavouriteToggle: (Todo) -> Unit // Callback to toggle favourite
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(todo.task, style = MaterialTheme.typography.body1)
        }

        // Heart (Favourite) button
        IconButton(onClick = { onFavouriteToggle(todo) }) {
            Icon(
                if (todo.isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Toggle Favourite"
            )
        }

        // Edit button (icon)
        IconButton(onClick = { onEdit(todo) }) {
            Icon(Icons.Default.Edit, contentDescription = "Edit Task")
        }

        // Delete button (icon)
        IconButton(onClick = { onDelete(todo) }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Task")
        }
    }
}




