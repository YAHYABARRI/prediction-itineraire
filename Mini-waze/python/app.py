from flask import Flask, request, jsonify
import joblib
import numpy as np

app = Flask(__name__)

model = joblib.load("model.pkl")

@app.route("/predict", methods=["POST"])
def predict():
    try:
        data = request.json

        input_data = np.array([[ 
            float(data["start_lat"]),
            float(data["start_lon"]),
            float(data["end_lat"]),
            float(data["end_lon"]),
            float(data["distance"]),
            float(data["hour"])
        ]])

        prediction = float(model.predict(input_data)[0])

        return jsonify({
            "duration_seconds": prediction,
            "duration_minutes": prediction / 60
        })

    except Exception as e:
        return jsonify({
            "error": str(e)
        }), 400

if __name__ == "__main__":
    app.run(debug=True, port=5000)