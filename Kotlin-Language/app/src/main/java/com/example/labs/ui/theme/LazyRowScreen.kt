package edu.miu.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.miu.myapplication.data.DataSource

@Composable
fun LazyRowScreen(modifier: Modifier = Modifier) {
    Scaffold{ innerPadding ->
        LazyRow (
            modifier = modifier.padding(innerPadding)
        ){
            items(DataSource.getData()) {
                Card {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
//                            .width(400.dp)
                            .height(200.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = it.image),
                        contentDescription = stringResource(it.title),
                    )
                    Text(
                        text = stringResource(id = it.title),
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyRowScreenPreview(modifier: Modifier = Modifier) {
    LazyRowScreen()
}
