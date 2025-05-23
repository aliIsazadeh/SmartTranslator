package com.esum.feature.image_convertor.presentation.screen

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.esum.common.constraints.TranslateFeature
import com.esum.core.ui.use
import com.esum.feature.image_convertor.presentation.R
import com.esum.feature.image_convertor.presentation.viewModel.CameraScreenContract
import com.esum.feature.image_convertor.presentation.viewModel.ImageTranslateViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.flow.Flow
import com.esum.core.ui.R as UiR


@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
fun CameraScreen(
    navController: NavController,
    windowSizeClass: WindowSizeClass,
    viewModel: ImageTranslateViewModel = hiltViewModel()
) {

    val (state, effect, event) = use(viewModel = viewModel)

    CameraScreen(
        state = state,
        effect = effect,
        event = event,
        onTextChange = viewModel::onTextChange,
        navController
    )

}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    state: CameraScreenContract.State,
    effect: Flow<CameraScreenContract.Effect>,
    event: (CameraScreenContract.Event) -> Unit,
    onTextChange: (String) -> Unit,
    navController: NavController
) {

    val context = LocalContext.current
    val previewView: PreviewView = remember { PreviewView(context) }
    val cameraController = remember { LifecycleCameraController(context) }
    val lifecycleOwner = LocalLifecycleOwner.current
    cameraController.bindToLifecycle(lifecycleOwner)
    cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    previewView.controller = cameraController


    val textRecognizer =
        remember { TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) }


    val permissionsCameraState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
        )
    )

    var selectSourceDialog by remember {
        mutableStateOf(true)
    }

    var textDialog by remember{
        mutableStateOf(false)
    }

    val filePermissionRequest = rememberMultiplePermissionsState(
        permissions =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            listOf(READ_MEDIA_IMAGES)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(READ_MEDIA_IMAGES)
        } else {
            listOf(READ_EXTERNAL_STORAGE)
        }
    )
    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            event.invoke(CameraScreenContract.Event.ImageCapture)
            // Use the cropped image
            //imageUri = result.uriContent
            result.uriContent?.let {
                val inputImage = InputImage.fromFilePath(context, it)
                textRecognizer.process(inputImage).addOnCompleteListener { task ->
                    val text =
                        if (!task.isSuccessful) task.exception!!.localizedMessage?.toString()
                            .toString()
                        else task.result.text
                    textDialog = true
                    selectSourceDialog = false
                    event.invoke(CameraScreenContract.Event.TextDetected(text))
                }
            }
        } else {
            Log.e("CameraScreen", "CameraScreen: ${result.error?.message} ")
            // Handle error
            val exception = result.error
        }
    }

    val requestForImage =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                val cropOptions = CropImageContractOptions(uri, CropImageOptions())
                imageCropLauncher.launch(cropOptions)

            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }


    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    permissionsCameraState.launchMultiplePermissionRequest()
                    filePermissionRequest.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)


            }
        }
    )

    if (selectSourceDialog) {
        Dialog(onDismissRequest = {
            selectSourceDialog = false
            navController.navigateUp()
        }) {

            Card(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.select_image_from))
                        Icon(imageVector = Icons.Filled.Photo, contentDescription = "")
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (permissionsCameraState.allPermissionsGranted) {
                                    val cropOptions = CropImageContractOptions(
                                        uri = null,
                                        cropImageOptions = CropImageOptions(
                                            imageSourceIncludeCamera = true,
                                            imageSourceIncludeGallery = false
                                        )
                                    )
                                    imageCropLauncher.launch(cropOptions)

                                } else {
                                    filePermissionRequest.launchMultiplePermissionRequest()
                                }
                            }, shape = MaterialTheme.shapes.medium
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(id = R.string.camera),
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                )
                                Icon(
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                    imageVector = Icons.Filled.CameraAlt,
                                    contentDescription = ""
                                )

                            }
                        }
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (filePermissionRequest.allPermissionsGranted) {
                                    requestForImage.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                } else {
                                    filePermissionRequest.launchMultiplePermissionRequest()
                                }
                            },
                            shape = MaterialTheme.shapes.medium,
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(id = R.string.files),
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                )
                                Icon(
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                    imageVector = Icons.Filled.UploadFile,
                                    contentDescription = ""
                                )

                            }
                        }
                    }
                }
            }
        }
    }
    if (state.loading) {
        Dialog(onDismissRequest = { }) {
            Card(shape = MaterialTheme.shapes.medium) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.analyzing))

                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            UiR.raw.translating
                        )
                    )
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier.size(120.dp),
                        iterations = 3,
                        restartOnPlay = false,
                    )

                }

            }

        }
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(UiR.raw.translating))
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(36.dp),
            iterations = 3,
            restartOnPlay = false,
        )

    }
    if (permissionsCameraState.allPermissionsGranted) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())

        }
    }
    if (state.text.isNotBlank() && textDialog) {
//        Box {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//        Dialog(
//            properties = DialogProperties(
//                usePlatformDefaultWidth = false
//            ),
//            onDismissRequest = { textDialog = false},
//
//            ) {
            Scaffold (
                modifier = Modifier
                    .fillMaxSize() ,
                bottomBar = {
                    Column(Modifier.padding(horizontal = 16.dp , vertical = 16.dp)) {
                        Button(
                            onClick = { navController.navigate(TranslateFeature.translatePageRoute + "/${state.text}") },
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier
                                .fillMaxWidth()
                            //.align(Alignment.End)
                        ) {
                            Text(text = stringResource(id = R.string.comfirm))
                        }
                        OutlinedButton(
                            onClick = {
                                onTextChange("")
                                selectSourceDialog = true
                            },
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier
                                .fillMaxWidth()
                            //.align(Alignment.End)

                        ) {
                            Text(text = stringResource(id = R.string.try_againg))
                        }
                    }
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(bottom = padding.calculateBottomPadding() , start = 16.dp , end = 16.dp , top = 16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    TextField(
                        value = state.text,
                        onValueChange = { onTextChange(it) },
                        modifier = Modifier
                    )

                }
            }
//        }
    }
    //   }
    //   }

}