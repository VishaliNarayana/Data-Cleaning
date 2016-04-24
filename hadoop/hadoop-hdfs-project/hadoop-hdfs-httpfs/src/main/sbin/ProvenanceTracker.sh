#!bin/sh
count=3
stmt=""
if [ "$#" -gt $count ]
then
  echo "error in input"
else
for i in $@
do
stmt="$stmt $i"
done
java -cp /home/vishal/Desktop/hadoop/DUT/src/edu/okstate/cs/EHL ProvenanceTracker $stmt
fi
