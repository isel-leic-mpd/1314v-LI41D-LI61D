/**
 *
 */
package isel.mpd.binding;


/**
 * @author lfalcao
 *
 */
public class CompositeBinder implements IBinderStrategy {

	private IBinderStrategy[] binderStrategies;

	/**
	 * @param fieldsBinder
	 * @param propertiesBinder
	 */
	public CompositeBinder(IBinderStrategy ... binderStrategies){
		this.binderStrategies = binderStrategies; 
	}

	/* (non-Javadoc)
	 * @see isel.mpd.binding.IBinderStrategy#bindMember(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T> boolean bindMember(T newT, String key, Object value) {
		for (IBinderStrategy binderStrategy : binderStrategies) {
			if(binderStrategy.bindMember(newT, key, value)) {
				return true;
			}
		}
		return false;
	}

}
