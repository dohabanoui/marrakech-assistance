# Import The necessary Libraries
# numpy for mathematical Operation
# rank_bm25 for indexing the corpus "Database"
# Os is used in order to read the csv data from the local files


import numpy as np
import pandas as pd
from rank_bm25 import BM25Okapi
from os.path import dirname , join

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

def hotels_index(q):
        index=q.split(",")
        index=[int(j) for j in index]
        res1=[]
        filename=join(dirname(__file__),'HotelsFinal.csv')
        df=pd.read_csv(filename,index_col=0)
        df=df.fillna(" ")
        for i in index:
            res1.append([df["Names"][i],df["Type"][i],df["Stars"][i],df["Description"][i],df["address"][i],df["Tel"][i],\
            df["website"][i],df["near_res"][i],df["near_att"][i],df["Properties"][i]\
            ,df["Styles"][i],str(df["id"][i]),df["gps"][i]])
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

def restos_index(q):
        index=q.split(",")
        index=[int(j) for j in index]
        res2=[]
        filename=join(dirname(__file__),'RestaurantsFinal.csv')
        df=pd.read_csv(filename,index_col=0)
        df=df.fillna(" ")
        for i in index:
               res2.append([df["Names"][i],df["ad_adress"][i],df["ad_about"][i],\
               df["ad_Phone"][i],df["ad_WebSite"][i],df["ad_cuisine"][i],df["ad_features"][i],df["ad_meals"][i],\
               df["ad_prices"][i],df["guru_time"][i],df["near_att"][i],df["near_hot"][i],df["near_res"][i],\
               df["special_diets"][i],str(df["id"][i]),df["gps"][i]])
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

def attractions_index(q):
        index=q.split(",")
        index=[int(j) for j in index]
        res3=[]
        filename=filename=join(dirname(__file__),'AttractionsFinal.csv')
        df=pd.read_csv(filename,index_col=0)
        df=df.fillna(" ")

        for i in index:
            res3.append([df["Names"][i],df["category"][i],df["adress"][i],df["description"][i],df["WebSite"][i],\
            df["Suggested duration"][i],df["open during"][i],df["near_res"][i],df["near_att"][i],\
            str(df["id"][i]),df["gps"][i]])
        return tuple(res3)

def hotel_names(query):
        query=query.lower()

        tokenized_query = query.split(" ")
        res1=[]
        filename=join(dirname(__file__),'HotelsFinal.csv')
        df=pd.read_csv(filename)
        df=df.fillna(" ")
        sentences=df["Names"].to_list()
        sentences=[ s.lower() for s in sentences]
        corpus = sentences
        tokenized_corpus = [doc.split(" ") for doc in corpus]
        index=[corpus.index(i) for i in corpus]
        bm25 =BM25Okapi(tokenized_corpus)
        doc_scores = bm25.get_scores(tokenized_query)
        l=bm25.get_top_n(tokenized_query,index, n=1000)
        for i in l:
            res1.append([df["Names"][i],df["Type"][i],df["Stars"][i],df["Description"][i],df["address"][i],\
            df["Tel"][i],df["website"][i],df["near_res"][i],df["near_att"][i],df["Properties"][i],\
            df["Styles"][i],str(df["id"][i]),df["gps"][i]])
        return tuple(res1)

def resto_names(query):
        query=query.lower()

        tokenized_query = query.split(" ")
        res2=[]
        filename=join(dirname(__file__),'RestaurantsFinal.csv')
        df=pd.read_csv(filename)
        df=df.fillna(" ")
        sentences=df["Names"].to_list()
        sentences=[ s.lower() for s in sentences]
        corpus = sentences
        tokenized_corpus = [doc.split(" ") for doc in corpus]
        index=[corpus.index(i) for i in corpus]
        bm25 =BM25Okapi(tokenized_corpus)
        doc_scores = bm25.get_scores(tokenized_query)
        l=bm25.get_top_n(tokenized_query,index, n=1000)
        for i in l:
               res2.append([df["Names"][i],df["ad_adress"][i],df["ad_about"][i],\
               df["ad_Phone"][i],df["ad_WebSite"][i],df["ad_cuisine"][i],df["ad_features"][i],df["ad_meals"][i],\
               df["ad_prices"][i],df["guru_time"][i],df["near_att"][i],df["near_hot"][i],df["near_res"][i],\
               df["special_diets"][i],str(df["id"][i]),df["gps"][i]])
        return tuple(res2)

def attraction_names(query):
        query=query.lower()

        tokenized_query = query.split(" ")
        res3=[]
        filename=filename=join(dirname(__file__),'AttractionsFinal.csv')
        df=pd.read_csv(filename,index_col=0)
        df=df.fillna(" ")
        sentences=df["Names"].to_list()
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
            df["Suggested duration"][i],df["open during"][i],df["near_res"][i],df["near_att"][i],\
            str(df["id"][i]),df["gps"][i]])
        return tuple(res3)