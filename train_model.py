import tensorflow as tf
import numpy as np
import tensorflowjs as tfjs

# Create a simple neural network model
model = tf.keras.Sequential([
    tf.keras.layers.Dense(32, activation='relu', input_shape=(4,)),
    tf.keras.layers.Dense(3, activation='softmax')
])

# Compile the model
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

# Create some random training data
x_train = np.random.random((100, 4))
y_train = np.random.randint(3, size=(100, 1))

# Convert labels to one-hot encoding
y_train = tf.keras.utils.to_categorical(y_train, num_classes=3)

# Train the model
model.fit(x_train, y_train, epochs=5)

# Convert and save the model for TensorFlow.js
tfjs.converters.save_keras_model(model, 'model')
