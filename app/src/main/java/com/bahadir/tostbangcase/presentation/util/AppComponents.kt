package com.bahadir.tostbangcase.presentation.util

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.presentation.theme.Purple40
import com.bahadir.tostbangcase.presentation.theme.Purple80
import com.bahadir.tostbangcase.presentation.theme.PurpleGrey40

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        color = colorResource(id = R.color.black_body),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        ),
        color = colorResource(id = R.color.black_header),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = true,
    @StringRes errorMessage: Int? = null,
) {
    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(8.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small),
            label = { Text(text = labelValue) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                focusedLabelColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primary,
                backgroundColor = Color.White,

            ),

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true,
            maxLines = 1,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                onTextChanged(it)
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription = "")
            },
            isError = errorStatus,
        )
        errorMessage?.let {
            Text(
                text = stringResource(id = it),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, top = 0.dp),
            )
        }
    }
}

@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
) {
    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            focusedLabelColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.primary,
            backgroundColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Home
            } else {
                Icons.Filled.Home
            }

            val description = if (passwordVisible.value) {
                "Hide Password"
            } else {
                "Show Password"
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus,
    )
}

@Composable
fun CheckboxComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            },
        )

        ClickableTextComponent(value = value, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
            Log.d("ClickableTextComponent", "{${span.item}}")

            if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                onTextSelected(span.item)
            }
        }
    })
}

@Composable
fun ButtonComponent(
    value: String,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            Log.e("ButtonComponent", "ButtonComponent")
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(50.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp,
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = "OR",
            fontSize = 16.sp,
            color = Color.Black,
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp,
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colors.onPrimary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 25.dp),
        style = TextStyle(
            fontSize = 19.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")

                if (span.item == loginText) {
                    onTextSelected(span.item)
                }
            }
        },
    )
}

@Composable
fun UnderLinedTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        color = Color.Gray,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
    )
}

@Composable
fun AppToolbar(
    toolbarTitle: String,
    logoutButtonClicked: () -> Unit,
    navigationIconClicked: () -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = {
            Text(
                text = toolbarTitle, color = Color.White,
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                )
            }
        },
        actions = {
            IconButton(onClick = {
                logoutButtonClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "LogOut",
                )
            }
        },
    )
}

@Composable
fun CustomDialogUI(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    @DrawableRes icon: Int? = null,
    body: String,
    title: String,
) {
    Card(
        // shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp,
    ) {
        Column(
            modifier
                .background(Color.White),
        ) {
            // .......................................................................
            icon?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(
                        color = Purple40,
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .height(70.dp)
                        .fillMaxWidth(),

                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                androidx.compose.material3.Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                androidx.compose.material3.Text(
                    text = body,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body2,
                )
            }
            // .......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Purple80),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                androidx.compose.material3.TextButton(onClick = {
                    openDialogCustom.value = false
                }) {
                    androidx.compose.material3.Text(
                        "Not Now",
                        fontWeight = FontWeight.Bold,
                        color = PurpleGrey40,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
                androidx.compose.material3.TextButton(onClick = {
                    openDialogCustom.value = true
                }) {
                    androidx.compose.material3.Text(
                        "Allow",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
            }
        }
    }
}
//
// @Composable
// fun NavigationDrawerHeader(value: String?) {
//    Box(
//        modifier = Modifier
//            .background(
//              Brush.horizontalGradient(
//                    listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)
//                )
//            )
//            .fillMaxWidth()
//            .height(180.dp)
//            .padding(32.dp)
//    ) {
//
//        NavigationDrawerText(
//            title = value?:"Navigation Header", 28.sp , MaterialTheme.colors.surface
//        )
//
//    }
// }

// @Composable
// fun NavigationDrawerBody(navigationDrawerItems: List<NavigationItem>,
//                         onNavigationItemClicked:(NavigationItem) -> Unit) {
//    LazyColumn(modifier = Modifier.fillMaxWidth()) {
//
//        items(navigationDrawerItems) {
//            NavigationItemRow(item = it,onNavigationItemClicked)
//        }
//
//    }
// }
//
// @Composable
// fun NavigationItemRow(item: NavigationItem,
//                      onNavigationItemClicked:(NavigationItem) -> Unit) {
//
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                onNavigationItemClicked.invoke(item)
//            }.padding(all = 16.dp)
//    ) {
//
//        Icon(
//            imageVector = item.icon,
//            contentDescription = item.description,
//        )
//
//        Spacer(modifier = Modifier.width(18.dp))
//
//        NavigationDrawerText(title = item.title, 18.sp, Primary)
//
//
//    }
// }
//
// @Composable
// fun NavigationDrawerText(title: String, textUnit: TextUnit,color: Color) {
//
//    val shadowOffset = Offset(4f, 6f)
//
//    Text(
//        text = title, style = TextStyle(
//            color = Color.Black,
//            fontSize = textUnit,
//            fontStyle = FontStyle.Normal,
//            shadow = Shadow(
//                color = Primary,
//                offset = shadowOffset, 2f
//            )
//        )
//    )
// }
