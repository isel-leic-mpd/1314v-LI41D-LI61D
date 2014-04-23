/**
 *
 */
package isel.mpd.evaluationsforms.form1;

/**
 * @author lfalcao
 *
 */
public class ClassWithProperties {
	private String prop1;
	private String foo;

	/**
	 * @return the prop1
	 */
	public String getProp1() {
		return prop1;
	}

	/**
	 * @param prop1 the prop1 to set
	 */
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	/**
	 * @return the prop2
	 */
	public String getProp2() {
		return foo;
	}

	/**
	 * @param prop2 the prop2 to set
	 */
	@BackingField("foo")
	public void setProp2(String prop2) {
		this.foo = prop2;
	}

}
