package sebastian.blanchet.tareasapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun SearchBar(
    searchInput: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onSortOptionSelected: (Option) -> Unit,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false)}

    OutlinedTextField(
        value = searchInput,
        onValueChange = onSearchInputChanged,
        placeholder = {
            Text(
                text = stringResource(
                    R.string.search_placeholder
                )
            )
        },
        trailingIcon = {
            Row{
                IconButton(onClick = onSearchClicked) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(
                            R.string.search_button_desc
                        )
                    )
                }
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = stringResource(R.string.filter)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = {Text(stringResource(R.string.newest))},
                            onClick = {
                                onSortOptionSelected(Option.NEWEST)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.older)) },
                            onClick = {
                                onSortOptionSelected(Option.OLDEST)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.az)) },
                            onClick = {
                                onSortOptionSelected(Option.AZ)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.za)) },
                            onClick = {
                                onSortOptionSelected(Option.ZA)
                                expanded = false
                            }
                        )
                    }
                }
            }
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}