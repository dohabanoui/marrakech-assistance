from os.path import join, dirname
import numpy as np
import pandas as pd
from rank_bm25 import BM25Okapi
import csv
import os

def getHotels():
    filename = join(dirname(__file__), 'HotelsFinalV2.csv')
    data = pd.read_csv(filename)
    data.fillna(' ')
    return list(data.to_dict(orient='records'))

def getRestaurants():
    filename = join(dirname(__file__), 'RestaurantsFinalV2.csv')
    data = pd.read_csv(filename)
    data.fillna(' ')
    return list(data.to_dict(orient='records'))

def getAttractions():
    filename = join(dirname(__file__), 'AttractionsFinalV2.csv')
    # filename = 'AttractionsFinalV2.csv'
    data = pd.read_csv(filename).fillna(' ')
    res= []
    #for i in range(len(df)):
        #res.append([df["Names"][i],df["category"][i],df["adress"][i],df["description"][i],df["WebSite"][i], \
                    # df["Suggested duration"][i],df["open during"][i],df["near_res"][i], \
                    # df["near_att"][i],df["info"][i]])
    return list(data.to_dict(orient='records'))


def getTransport():
    filename = join(dirname(__file__), 'lines.csv')
    data = pd.read_csv(filename).fillna(' ')
    return list(data.to_dict(orient='records'))

def get_stations_by_line(line_id):
    filename = join(dirname(__file__), 'line_stat.csv')
    # filename = 'line_stat.csv'
    line_id = int(line_id)
    df = pd.read_csv(filename)
    filtered_rows = df[df['ligne_id'] == line_id]
    return filtered_rows.to_dict(orient='records')

def hotels(query):
        # Initialize an empty list to store the results data
        res1=[]

        # Process The query
        # query=query.replace("stars","")
        query=query.lower()
        tokenized_query = query.split(" ")

        # Read the csv file from the local
        filename=join(dirname(__file__),'HotelsFinalV2.csv')
        df=pd.read_csv(filename)
        df=df.fillna(" ")

        # In this part we're getting the info colum
        # it represent the features that will be embedded in order to index the database
        sentences= df["info"].to_list()
        sentences=[s.lower() for s in sentences]
        corpus = sentences
        tokenized_corpus = [doc.split(" ") for doc in corpus]
        index=[corpus.index(i) for i in corpus]

        bm25 =BM25Okapi(tokenized_corpus)
        doc_scores = bm25.get_scores(tokenized_query)
        l=bm25.get_top_n(tokenized_query,index, n=1000)
        for i in l:
            res1.append([df["Names"][i],df["Type"][i],df["Stars"][i],df["Description"][i],df["address"][i],\
            df["Tel"][i],df["website"][i],df["near_res"][i],df["near_att"][i],df["Properties"][i],df["Styles"][i],\
            df["img_url"][i],df["gps"][i]])
        return tuple(res1)


def restos(query):
        query=query.lower()
        tokenized_query = query.split(" ")
        res2=[]
        filename=join(dirname(__file__),'RestaurantsFinalV2.csv')
        df=pd.read_csv(filename)
        df=df.fillna(" ")
        sentences=df["info"].to_list()
        sentences=[ s.lower() for s in sentences]
        corpus = sentences
        tokenized_corpus = [doc.split(" ") for doc in corpus]
        index=[corpus.index(i) for i in corpus]
        bm25 =BM25Okapi(tokenized_corpus)
        doc_scores = bm25.get_scores(tokenized_query)
        l=bm25.get_top_n(tokenized_query,index, n=1000)
        for i in l:
               res2.append([df["Names"][i],df["ad_adress"][i],df["ad_about"][i],df["ad_Phone"][i],df["ad_WebSite"][i],\
               df["ad_cuisine"][i],df["ad_features"][i],df["ad_meals"][i],df["ad_prices"][i],\
               df["guru_time"][i],df["near_att"][i],df["near_hot"][i],df["near_res"][i],df["special_diets"][i],\
               df["img_url"][i],df["gps"][i]])
        return tuple(res2)


def attractions(query):
        query=query.lower()

        tokenized_query = query.split(" ")
        res3=[]
        filename=filename=join(dirname(__file__),'AttractionsFinalV2.csv')
        df=pd.read_csv(filename)
        df=df.fillna(" ")
        sentences=df["info"].to_list()
        tokenized_query = query.split(" ")
        sentences=[ s.lower() for s in sentences]
        corpus = sentences
        tokenized_corpus = [doc.split(" ") for doc in corpus]
        index=[corpus.index(i) for i in corpus]
        bm25 =BM25Okapi(tokenized_corpus)
        doc_scores = bm25.get_scores(tokenized_query)
        l=bm25.get_top_n(tokenized_query,index, n=58)
        for i in l:
            res3.append([df["Names"][i],df["category"][i],df["adress"][i],df["description"][i],df["WebSite"][i],\
            df["Suggested duration"][i],df["open during"][i],df["near_res"][i],\
            df["near_att"][i],df["img_url"][i],df["gps"][i]])
        return tuple(res3)


def add_to_favorites(title, address, description, pl_type, imgUrl):
    file_path = join(dirname(__file__),'favorites.csv')  # Path to the CSV file

    # Create a dictionary with the data for the new entry
    new_entry = {
        'title': [title],
        'address': [address],
        'description': [description],
        'type': [pl_type],
        'imgUrl': [imgUrl]
    }

    # Create a DataFrame from the new entry
    df = pd.DataFrame(new_entry)

    # Check if the CSV file exists
    try:
        existing_data = pd.read_csv(file_path)
        df = pd.concat([existing_data, df], ignore_index=True)
    except FileNotFoundError:
        # File doesn't exist, create a new file with the new entry
        pass

    # Write the DataFrame to the CSV file
    df.to_csv(file_path, index=False)


def get_all_favorites():
    file_path = join(dirname(__file__),'favorites.csv')  # Path to the CSV file

    # Read the CSV file into a pandas DataFrame
    df = pd.read_csv(file_path)

    # Return the DataFrame as a list of dictionaries
    favorites = df.to_dict(orient='records')

    return favorites


def remove_from_favorites(title):
    file_path = join(dirname(__file__),'favorites.csv')  # Path to the CSV file

    # Read the CSV file into a pandas DataFrame
    df = pd.read_csv(file_path)

    # Filter the DataFrame to exclude the rows with the specified title
    df = df[df['title'] != title]

    # Write the updated DataFrame back to the CSV file
    df.to_csv(file_path, index=False)