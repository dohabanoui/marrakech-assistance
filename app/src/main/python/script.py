from os.path import join, dirname
import numpy as np
import pandas as pd
from rank_bm25 import BM25Okapi


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
    # filename = join(dirname(_file_), 'AttractionsFinal.csv')
    filename = 'AttractionsFinalV2.csv'
    df = pd.read_csv(filename)
    res= []
    for i in range(len(df)):
        res.append([df["Names"][i],df["category"][i],df["adress"][i],df["description"][i],df["WebSite"][i], \
                    df["Suggested duration"][i],df["open during"][i],df["near_res"][i], \
                    df["near_att"][i],df["info"][i]])
    return tuple(res)


def getTransport():
    filename = join(dirname(__file__), 'line_stat.csv')
    data = pd.read_csv(filename)
    data.fillna(' ')
    return list(data.to_dict(orient='records'))

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
