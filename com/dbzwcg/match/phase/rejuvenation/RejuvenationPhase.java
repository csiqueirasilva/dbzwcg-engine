/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.rejuvenation;

import com.dbzwcg.match.phase.Phase;
import com.dbzwcg.types.PhaseType;

/**
 *
 * @author csiqueira
 */
public class RejuvenationPhase extends Phase {

    private Boolean rejuvenated;

    public RejuvenationPhase() {
        super.type = PhaseType.REJUVENATION;
    }

    public Boolean getRejuvenated() {
        return rejuvenated;
    }

    public void setRejuvenated(Boolean rejuvenated) {
        this.rejuvenated = rejuvenated;
    }

    @Override
    public String getName() {
        return "REJUVENATION PHASE";
    }
}
