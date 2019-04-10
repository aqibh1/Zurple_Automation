/**
 * 
 */
package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author adar
 *
 */
public class PPMLSListingData extends JSONData<PPMLSListingData>{
	
	@JsonProperty("mls")
	String mls;
	
	@JsonProperty("mls_board")
	String mls_board;
	
	public String getMls() {
		return mls;
	}
	public String getMls_board() {
		return mls_board;
	}
	public void setMls(String mls) {
		this.mls = mls;
	}
	public void setMls_board(String mls_board) {
		this.mls_board = mls_board;
	}
	PPMLSListingData listingData;
	public PPMLSListingData() {
		// TODO Auto-generated constructor stub
	}
	public PPMLSListingData(String pFileName){
		listingData=(PPMLSListingData) setDataMapper(pFileName, PPMLSListingData.class);
	}
	public PPMLSListingData getListingData() {
		return listingData;
	}
}
