import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error
import joblib

# ✅ 1. charger le dataset AVANT tout
df = pd.read_csv("dataset.csv")

# ✅ 2. (optionnel) vérifier
print(df.head())

# ✅ 3. définir X et y
X = df[["start_lat", "start_lon", "end_lat", "end_lon", "distance", "hour"]]
y = df["duration"]

# split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# modèle
model = RandomForestRegressor(n_estimators=100, random_state=42)
model.fit(X_train, y_train)

# test
predictions = model.predict(X_test)

error = mean_absolute_error(y_test, predictions)
print("Erreur moyenne :", error)

# sauvegarde
joblib.dump(model, "model.pkl")

print("✅ Modèle sauvegardé")