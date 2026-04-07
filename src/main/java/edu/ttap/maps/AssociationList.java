package edu.ttap.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An association list is an implementation of a map via a list of key-value pairs.
 */
public class AssociationList<K, V> implements Map<K, V> {
    public class Pair {
    public K fst;
    public V snd;
    public Pair(K fst, V snd) {
        this.fst = fst;
        this.snd = snd;
    }
}
g
    private ArrayList<Pair> lst;

    public AssociationList (ArrayList lst) {
        this.lst = lst;
    }
    /**
     * Clears the association list, removing all key-value pairs.
     */
    @Override
    public void clear() {
        lst.clear();
    }

    /**
     * @param key the key to check
     * @return true iff this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(Object key) {
        for(Pair p : lst) {
            if( (p.fst).equals(key) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param value the value to check
     * @return true iff this map maps one or more keys to the specified value
     */
    @Override
    public boolean containsValue(Object value) {
        for(Pair p : lst) {
            if( (p.snd).equals(value) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        // NOTE: you do not need to implement this method!
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

    /**
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key
     */
    @Override
    public V get(Object key) {
        V val = null;
        for(Pair p : lst) {
            if( (p.fst).equals(key) ) {
                val = p.snd;
            }
        }
        return val;
    }

    /**
     * @return true iff this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return lst.isEmpty();
    }

    /**
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        HashSet<K> hash = new HashSet();
        for(Pair p : lst) {
            hash.add(p.fst);
        }
        return hash;
    }

    /**
     * If there is no entry for key in the map, updates the entry to associate key
     * with value. Otherwise, it updates the entry for key accordingly.
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    @Override
    public V put(K key, V value) {
        V prev = null;
        for(Pair p : lst) {
            if(p.fst.equals(key)) {
                prev = p.snd;
                p.snd = value;
            }
        }
        if(prev == null) {
            lst.add(new Pair(key, value));
        }
        return prev;
    }

    /**
     * Copies all of the mappings from the specified map to this map. The effect of this
     * operation is equivalent to applying the put(K, V) operation to each entry in the
     * specified map.
     * @param m the map whose mappings are to be copied to this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO: implement me!
        throw new UnsupportedOperationException("Unimplemented method 'putAll'");
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for
     *         key.
     */
    @Override
    public V remove(Object key) {
        V prev = null;
        for(Pair p : lst) {
            if(p.fst.equals(key)) {
                prev = p.snd;
                lst.remove(p);
            }
        }
        return prev;
    }

    /**
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        int mapsize = 0;
        for(Pair p : lst) {
           mapsize++; 
        }
        return mapsize;
    }

    /**
     * @return a collection vof the values contained in this map, e.g., a list
     */
    @Override
    public Collection<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for(Pair p : lst) {
            values.add(p.snd);
        }
        return values;
    }
}
