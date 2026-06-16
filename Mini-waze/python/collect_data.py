import requests
import pandas as pd
import random
import time

# Zone Casablanca
lat_min, lat_max = 33.55, 33.65
lon_min, lon_max = -7.65, -7.55

data = []

# Nombre de lignes
NB_ROWS = 3000

for i in range(NB_ROWS):

    # Génération des coordonnées
    start_lat = random.uniform(lat_min, lat_max)
    start_lon = random.uniform(lon_min, lon_max)

    end_lat = random.uniform(lat_min, lat_max)
    end_lon = random.uniform(lon_min, lon_max)

    # URL OSRM
    url = f"http://router.project-osrm.org/route/v1/driving/{start_lon},{start_lat};{end_lon},{end_lat}?overview=false"

    try:
        response = requests.get(url)
        res = response.json()

        # Vérification
        if res["code"] != "Ok":
            print(f"{i} -> Erreur route")
            continue

        # Route
        route = res["routes"][0]

        distance = route["distance"]      # mètres
        base_duration = route["duration"] # secondes

        # Heure aléatoire
        hour = random.randint(0, 23)

        # Simulation trafic
        traffic_factor = 1.0

        # Heures de pointe matin
        if 7 <= hour <= 9:
            traffic_factor = random.uniform(1.4, 1.8)

        # Midi
        elif 12 <= hour <= 14:
            traffic_factor = random.uniform(1.1, 1.3)

        # Heures de pointe soir
        elif 17 <= hour <= 20:
            traffic_factor = random.uniform(1.5, 2.0)

        # Nuit
        elif 0 <= hour <= 5:
            traffic_factor = random.uniform(0.7, 0.9)

        # Durée finale avec trafic
        duration = base_duration * traffic_factor

        # Ajout dataset
        data.append([
            start_lat,
            start_lon,
            end_lat,
            end_lon,
            distance,
            duration,
            hour
        ])

        print(f"{i + 1}/{NB_ROWS} OK")

        # éviter trop de requêtes
        time.sleep(0.3)

    except Exception as e:
        print(f"{i} -> Erreur : {e}")

# Création DataFrame
df = pd.DataFrame(data, columns=[
    "start_lat",
    "start_lon",
    "end_lat",
    "end_lon",
    "distance",
    "duration",
    "hour"
])

# Sauvegarde CSV
df.to_csv("dataset.csv", index=False)

print("\nDataset créé avec succès !")
print(df.head())