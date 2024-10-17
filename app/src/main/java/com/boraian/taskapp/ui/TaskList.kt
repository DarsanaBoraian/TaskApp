package com.boraian.taskapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.boraian.taskapp.data.Todo

@Composable
fun TaskList(
    tasks: List<Todo>,
    onDelete: (Todo) -> Unit,
    onUpdate: (Todo) -> Unit,
    onFavouriteToggle: (Todo, Boolean) -> Unit
) {
    Column {
        tasks.forEach { todo ->
            TaskItem(
                todo = todo,
                onDelete = { onDelete(todo) },
                onUpdate = { onUpdate(todo) },
                onFavouriteToggle = { onFavouriteToggle(todo, it) }
            )
        }
    }
}

@Composable
fun TaskItem(
    todo: Todo,
    onDelete: () -> Unit,
    onUpdate: () -> Unit,
    onFavouriteToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(todo.task, style = MaterialTheme.typography.subtitle1)
            Text(
                if (todo.isFavourite) "Favourite" else "Not Favourite",
                style = MaterialTheme.typography.caption
            )
        }

        Checkbox(
            checked = todo.isFavourite,
            onCheckedChange = { onFavouriteToggle(it) }
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = onUpdate) {
            Icon(Icons.Default.Edit, contentDescription = "Edit Task")
        }

        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Task")
        }
    }
}
