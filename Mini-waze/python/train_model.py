from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_absolute_error
import pandas as pd
import joblib

# Charger dataset
df = pd.read_csv("dataset.csv")

# Features
X = df[[
    "start_lat",
    "start_lon",
    "end_lat",
    "end_lon",
    "distance",
    "hour"
]]

# Target
y = df["duration"]

# Split
X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.2,
    random_state=42
)

# Model
model = RandomForestRegressor(
    n_estimators=200,
    random_state=42
)

# Training
model.fit(X_train, y_train)

# Prediction
y_pred = model.predict(X_test)

# Evaluation
mae = mean_absolute_error(y_test, y_pred)

print(f"MAE : {mae:.2f} secondes")

# Save model
joblib.dump(model, "model.pkl")

print("Model saved successfully!")