/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;
import java.util.List;

import com.main.mart.common.dto.GrnDetailForm;
import com.main.mart.common.dto.GrnForm;
import com.main.mart.entity.Grn;
import com.main.mart.entity.GrnDetails;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface GrnEJBIf {
	/**
	 * Get List of GRN by filter fields data
	 * @param grnForm
	 * @return Collection<Grn>
	 */
	public Collection<Grn> getAllGrn(GrnForm grnForm);
	
	public List<GrnDetails> getGrnDetails(GrnDetailForm grnDetailForm);
	
	/**
	 * Getting Grn by Id
	 * @param id
	 * @return Grn
	 */
	public Grn getGrnById(Integer id);
	/**
	 * Persist Grn entity
	 * @param grn
	 * @return ResponseStatusTO
	 */
	public ResponseStatus addGrn(Grn grn);
	/**
	 * Merge Grn entity
	 * @param grn
	 * @return ResponseStatusTO
	 */
	public ResponseStatus updateGrn(Grn grn);
	/**
	 * Getting GrnDetails by Id
	 * @param id
	 * @return GrnDetails
	 */
	public GrnDetails getGrnDetailsById(Integer id);

}
