package com.example.hellocompose

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellocompose.ui.theme.Black
import com.example.hellocompose.ui.theme.LightBlue
import com.example.hellocompose.ui.theme.LightGrey
import com.example.hellocompose.ui.theme.Purple
import com.example.hellocompose.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val resourceList = ComposeResource.resourceList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        IntroText()
        IntroCard()
        BenefitSection()
        StartButton(context = context)
        Spacer(modifier = Modifier.height(24.dp))
        for (resource in resourceList) {
            ResourceCard(resource = resource, context = context)
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun IntroText() {
    Text(
        modifier = Modifier.padding(start = 24.dp, top = 32.dp, end = 24.dp),
        text = buildAnnotatedString {
            append("This is my first\n")
            withStyle(
                style = SpanStyle(
                    fontSize = 30.sp, fontWeight = FontWeight.ExtraBold
                )
            ) {
                append("Compose App")
            }
        },
        fontSize = 24.sp,
        fontWeight = FontWeight.Light,
        lineHeight = 40.sp
    )
}

@Composable
fun IntroCard() {
    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Purple)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(6f), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Jetpack Compose",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
            Text(
                text = "Android’s recommended modern toolkit for building native UI",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = White
            )
        }
        Image(
            modifier = Modifier
                .weight(4f)
                .heightIn(max = 160.dp),
            painter = painterResource(id = R.drawable.compose_logo),
            contentDescription = "jetpack compose logo",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun BenefitSection() {
    val composeBenefits = ComposeBenefit.benefitList
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(composeBenefits) { index, item ->
                Box(modifier = Modifier
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(50))
                    .background(if (selectedIndex == index) Black else LightGrey)
                    .clickable { selectedIndex = index }
                    .padding(horizontal = 24.dp, vertical = 12.dp)) {
                    Text(
                        text = item.title,
                        color = if (selectedIndex == index) White else Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp, vertical = 32.dp),
            text = composeBenefits[selectedIndex].description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun StartButton(context: Context) {
    val intent = Intent(
        Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/develop/ui/compose")
    )
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        OutlinedButton(
            onClick = { context.startActivity(intent) },
            border = BorderStroke(width = 2.dp, color = Black),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = "Get Started", color = Purple)
        }
    }
}

@Composable
fun ResourceCard(resource: ComposeResource, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resource.url))
    Row(modifier = Modifier
        .padding(horizontal = 24.dp, vertical = 6.dp)
        .fillMaxWidth()
        .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
        .background(LightBlue)
        .clickable { context.startActivity(intent) }
        .padding(start = 16.dp, end = 8.dp, top = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = resource.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(text = resource.description, fontSize = 12.sp, fontWeight = FontWeight.Light)
        }
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}