/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isel.mpd.binding;

/**
 *
 * @author lfalcao
 */

public interface IBinderStrategy {
    <T> boolean bindMember(T newT, String key, Object value);
}
