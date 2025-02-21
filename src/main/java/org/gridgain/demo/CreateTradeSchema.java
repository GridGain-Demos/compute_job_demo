package org.gridgain.demo;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.lang.IgniteRunnable;

public class CreateTradeSchema implements IgniteRunnable {

    private Ignite ignite_;

    public CreateTradeSchema(Ignite ignite) {ignite_ = ignite;};

    @Override
    public void run() {
        IgniteCache<Integer, Double> buyerTotalSpend = ignite_.cache("TRADE");
        if(null == buyerTotalSpend) {
            IgniteCache utilityCache = ignite_.getOrCreateCache(new CacheConfiguration("utilityCache"));

            utilityCache.query(new SqlFieldsQuery(
                    "CREATE TABLE TRADE (ID long, BUYER_ID int, SYMBOL varchar, ORDER_QUANTITY int, BID_PRICE double, TRADE_TYPE varchar, ORDER_DATE timestamp, PRIMARY KEY(ID, BUYER_ID)) WITH \"backups=1, atomicity=transactional, cache_name=Trade, affinity_key=BUYER_ID\"").
                    setSchema("PUBLIC")).getAll();

            SqlFieldsQuery query = new SqlFieldsQuery("INSERT INTO TRADE (ID, BUYER_ID, SYMBOL, ORDER_QUANTITY, BID_PRICE, TRADE_TYPE, ORDER_DATE) VALUES (?,?,?,?,?,?,?)").
                    setSchema("PUBLIC");

            utilityCache.query(query.setArgs(1, 1, "Google", 10, 100.0, "market", "2025-02-21 09:00:00"));
            utilityCache.query(query.setArgs(2, 1, "Google", 10, 100.0, "market", "2025-02-21 09:01:00"));
            utilityCache.query(query.setArgs(3, 2, "Aple", 10, 100.0, "market", "2025-02-21 09:02:00"));
            utilityCache.query(query.setArgs(4, 2, "Apple", 10, 100.0, "market", "2025-02-21 09:03:00"));
            utilityCache.query(query.setArgs(5, 3, "Amazon", 10, 100.0, "market", "2025-02-21 09:04:00"));
            utilityCache.query(query.setArgs(6, 3, "Amazon", 10, 100.0, "market", "2025-02-21 09:05:00"));
            utilityCache.query(query.setArgs(7, 4, "Dell", 10, 100.0, "market", "2025-02-21 09:06:00"));
            utilityCache.query(query.setArgs(8, 4, "Dell", 10, 100.0, "market", "2025-02-21 09:07:00"));
            utilityCache.query(query.setArgs(9, 5, "Spectrum", 10, 100.0, "market", "2025-02-21 09:08:00"));
            utilityCache.query(query.setArgs(10, 5, "Spectrum", 10, 100.0, "market", "2025-02-21 09:09:00"));
            utilityCache.query(query.setArgs(11, 6, "Yahoo", 10, 100.0, "market", "2025-02-21 09:10:00"));
            utilityCache.query(query.setArgs(12, 6, "Yahoo", 10, 100.0, "market", "2025-02-21 09:11:00"));
            utilityCache.query(query.setArgs(13, 7, "Walmart", 10, 100.0, "market", "2025-02-21 09:12:00"));
            utilityCache.query(query.setArgs(14, 7, "Walmart", 10, 100.0, "market", "2025-02-21 09:13:00"));
            utilityCache.query(query.setArgs(15, 8, "Alphabet", 10, 100.0, "market", "2025-02-21 09:14:00"));
            utilityCache.query(query.setArgs(16, 8, "Alphabet", 10, 100.0, "market", "2025-02-21 09:15:00"));
            utilityCache.query(query.setArgs(17, 9, "CVS", 10, 100.0, "market", "2025-02-21 09:16:00"));
            utilityCache.query(query.setArgs(18, 9, "CVS", 10, 100.0, "market", "2025-02-21 09:17:00"));
            utilityCache.query(query.setArgs(19, 10, "Exxon", 10, 100.0, "market", "2025-02-21 09:18:00"));
            utilityCache.query(query.setArgs(20, 10, "Exxon", 10, 100.0, "market", "2025-02-21 09:19:00"));
        }
    }

}
