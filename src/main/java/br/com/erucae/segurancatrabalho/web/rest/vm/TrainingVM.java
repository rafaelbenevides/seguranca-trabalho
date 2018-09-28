package br.com.erucae.segurancatrabalho.web.rest.vm;

import br.com.erucae.segurancatrabalho.service.dto.TrainingDTO;
import br.com.erucae.segurancatrabalho.service.dto.TrainingItemDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * View Model object for storing a Training with items.
 */
public class TrainingVM extends TrainingDTO {

    private Set<TrainingItemDTO> items = new HashSet<>();

    public TrainingVM() {
        // Empty public constructor used by Jackson.
    }

    public Set<TrainingItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<TrainingItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "TrainingVM{" +
            "items=" + items +
            '}';
    }
}
