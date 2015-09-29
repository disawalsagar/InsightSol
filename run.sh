path=$(pwd)
tweet_input='/tweet_input/tweets.txt';
tweet_output='/tweet_output';
complete_tweet_input_path=$path$tweet_input
complete_src_path=$path
complete_tweet_output_path=$path$tweet_output
cd src; javac WordCount.java;java WordCount $complete_tweet_input_path $complete_tweet_output_path; cd ..;
cd src; javac FindMedian.java;java FindMedian $complete_tweet_input_path $complete_tweet_output_path; cd ..;
