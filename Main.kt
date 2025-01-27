import org.w3c.dom.Window
import kotlinx.browser.document
import kotlinx.browser.window
import tensorflow.*  // TensorFlow.js

fun main() {
    // Load the model when the page is loaded
    window.onload = {
        loadModel()
    }
}

var model: LayerModel? = null

// Load TensorFlow.js model
fun loadModel() {
    // Assuming 'model/model.json' is available from the server
    model = tf.loadLayersModel("model/model.json").unsafeCast<LayerModel>()
    console.log("Model Loaded Successfully!")
    setupUI()
}

// Setup basic UI elements and button event
fun setupUI() {
    val button = document.createElement("button") as HTMLButtonElement
    button.innerHTML = "Make Prediction"
    button.onclick = {
        makePrediction()
    }

    val body = document.body!!
    body.appendChild(button)
}

// Function to make prediction
fun makePrediction() {
    if (model == null) {
        window.alert("Model is not loaded yet!")
        return
    }

    // Dummy input tensor for testing: shape (1, 4)
    val input = tf.tensor2d(arrayOf(arrayOf(0.1, 0.2, 0.3, 0.4)))

    // Make prediction
    val prediction = model?.predict(input)

    // Log the result in the console (you can update the DOM to show it)
    prediction?.asArray()?.let { 
        println("Prediction Result: ${it.joinToString()}")
        document.getElementById("prediction")?.textContent = "Prediction: ${it.joinToString()}"
    }
}
