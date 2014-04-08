/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isel.mpd.typesystem;

/**
 *
 * @author lfalcao
 */

interface BinderStrategy {
    <T> boolean bindMember(T newT, String key, Object value);
}
