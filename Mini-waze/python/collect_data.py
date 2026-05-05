import requests
import pandas as pd
import random
import time

# zone Casablanca
lat_min, lat_max = 33.55, 33.65
lon_min, lon_max = -7.65, -7.55

data = []

for i in range(300):
    start_lat = random.uniform(lat_min, lat_max)
    start_lon = random.uniform(lon_min, lon_max)
    end_lat = random.uniform(lat_min, lat_max)
    end_lon = random.uniform(lon_min, lon_max)

    url = f"http://router.project-osrm.org/route/v1/driving/{start_lon},{start_lat};{end_lon},{end_lat}?overview=false"

    try:
        response = requests.get(url)
        res = response.json()

        if res["code"] != "Ok":
            print("Erreur route")
            continue

        route = res["routes"][0]
        distance = route["distance"]
        duration = route["duration"]

        hour = random.randint(0, 23)

        data.append([start_lat, start_lon, end_lat, end_lon, distance, duration, hour])

        print(f"{i} OK")
        time.sleep(0.5)

    except Exception as e:
        print("Erreur:", e)

df = pd.DataFrame(data, columns=[
    "start_lat", "start_lon",
    "end_lat", "end_lon",
    "distance", "duration", "hour"
])

df.to_csv("dataset.csv", index=False)