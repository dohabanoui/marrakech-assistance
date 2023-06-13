from os.path import join, dirname
import numpy as np
import pandas as pd
from rank_bm25 import BM25Okapi


def get_hotels():
    filename = join(dirname(__file__), 'Hotels.csv')
    # filename = 'HotelsFinal.csv'
    data = pd.read_csv(filename).fillna(' ')
    return list(data.to_dict(orient='records'))

def get_restaurants():
    filename = join(dirname(__file__), "RestaurantsFinal.csv")
    data = pd.read_csv(filename).fillna(' ')
    return list(data.to_dict(orient='records'))

def get_attractions():
    filename = join(dirname(__file__), "AttractionsFinal.csv")
    data = pd.read_csv(filename).fillna(' ')
    return list(data.to_dict(orient='records'))


def to_lower(sample):
    return sample.lower()


def search_hotels(query='bab dokkala'):
    # Initialize an empty list to store the results data
    res= []

    # Process The query
    # query= query.replace("stars","")
    query= query.lower()
    tokenized_query= query.split(" ")

    # Read the csv file from the local
    # filename=join(dirname(__file__),'HotelsFinal.csv')
    filename = 'Hotels.csv'
    df= pd.read_csv(filename,index_col=0).fillna(' ')

    # In this part we're getting the info colum
    # it represent the features that will be embedded in order to index the database

    corpus = df["info"].apply(to_lower).to_list()
    tokenized_corpus = [doc.split(" ") for doc in corpus]
    index=[corpus.index(i) for i in corpus]
    bm25= BM25Okapi(tokenized_corpus)

    doc_scores = bm25.get_scores(tokenized_query)
    l = bm25.get_top_n(tokenized_query,index, n=60)

    for i in l:
        # get The Row by index and cast it to a dict
        res.append(df.iloc[i].to_dict())
    return list(res)


def search_restaurants(query):
    res=[]

    query= query.lower()
    tokenized_query= query.split(" ")

    # filename=join(dirname(__file__),'RestaurantsFinal.csv')
    filename = 'RestaurantsFinal.csv'
    df=pd.read_csv(filename,index_col=0)
    df=df.fillna(" ")

    corpus = df["info"].apply(to_lower).to_list()
    tokenized_corpus = [doc.split(" ") for doc in corpus]
    index=[corpus.index(i) for i in corpus]
    bm25 =BM25Okapi(tokenized_corpus)

    doc_scores = bm25.get_scores(tokenized_query)
    l=bm25.get_top_n(tokenized_query,index, n=300)

    for i in l:
           res.append(df.iloc[i].to_dict())
    return res


def search_attractions(query):
    res=[]

    query= query.lower()
    tokenized_query= query.split(" ")

    # filename=join(dirname(__file__),'RestaurantsFinal.csv')
    filename = 'AttractionsFinal.csv'
    df=pd.read_csv(filename,index_col=0)
    df=df.fillna(" ")

    corpus = df["info"].apply(to_lower).to_list()
    tokenized_corpus = [doc.split(" ") for doc in corpus]
    index=[corpus.index(i) for i in corpus]
    bm25 =BM25Okapi(tokenized_corpus)

    doc_scores = bm25.get_scores(tokenized_query)
    l=bm25.get_top_n(tokenized_query,index, n=300)

    for i in l:
           res.append(df.iloc[i].to_dict())
    return res


if __name__=="__main__":
     print(search_hotels("bab dokkala"))