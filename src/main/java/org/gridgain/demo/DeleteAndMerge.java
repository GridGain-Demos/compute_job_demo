package org.gridgain.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.lang.IgniteRunnable;

public class DeleteAndMerge implements IgniteRunnable {

    private Ignite ignite_;

    private LinkedList<Integer> idList_;

    private Random random_;

    private int currentMaxValue_;

    public DeleteAndMerge(Ignite ignite) {
        idList_ = new LinkedList<Integer>();
        random_ = new Random();
        currentMaxValue_ = 1024;
        ignite_ = ignite;
        initializeList();
    }

    @Override
    public void run() {
        IgniteCache utilityCache = ignite_.getOrCreateCache(new CacheConfiguration("utilityCache"));

        for(int i = 0; i < 10000; ++i) {
            System.out.println("Delete and merge loop #" + i);

            SqlFieldsQuery insert_statement = new SqlFieldsQuery("INSERT INTO BUYER_TOTAL_SPEND (buyer_id, total_spend) VALUES (?,?)").
                    setSchema("PUBLIC");

            SqlFieldsQuery delete_statement = new SqlFieldsQuery("DELETE FROM BUYER_TOTAL_SPEND WHERE buyer_id = ?").setSchema("PUBLIC");

            List<Integer> deletionIndexes = getRandomDeletionIndexes();
            for(Integer deletionIndex : deletionIndexes){
                delete_statement.setArgs(idList_.get(deletionIndex));
                utilityCache.query(delete_statement);
            }
            for(Integer deletionIndex : deletionIndexes){
                idList_.removeFirstOccurrence(idList_.get(deletionIndex));
            }
            for(int j = 0; j < 512; ++j) {
                utilityCache.query(insert_statement.setArgs(++currentMaxValue_, 0.0));
                idList_.add(currentMaxValue_);
            }
        }
    }

    private void initializeList() {
        for(int i = 1; i < 1025; ++i) {
            idList_.add(i);
        }
    }

    private List<Integer> getRandomDeletionIndexes() {
        ArrayList<Integer> results = new ArrayList<Integer>(512);
        for(int i = 0; i < 513; ++i) {
            results.add(random_.nextInt(1024));
        }
        return results;
    }

}
