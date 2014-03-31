/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isel.mpd.typesystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public abstract class AbstractBinder {
    
    	public final <T> T bindTo(Class<T> klass, Map<String, Object> vals) 
                throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
        {
		T newT = klass.newInstance();

		for (Map.Entry<String, Object> entry : vals.entrySet()) {
                    bindMember(newT, entry.getKey(), entry.getValue());
		}
		return newT;
	}

        protected static boolean isAssignable(Class<?> dstType, Class<?> srcType) {
		return Primitives.wrap(dstType).isAssignableFrom(srcType);
	}

        protected abstract <T> boolean bindMember(T newT, String key, Object value);

}
