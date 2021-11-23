package es.cuatrogatos.trex.entity;

public class Pci {
    private int pci_bus;
    private int pci_domain;
    private int pci_id;

    public int getPci_bus() {
        return pci_bus;
    }

    public void setPci_bus(int pci_bus) {
        this.pci_bus = pci_bus;
    }

    public int getPci_domain() {
        return pci_domain;
    }

    public void setPci_domain(int pci_domain) {
        this.pci_domain = pci_domain;
    }

    public int getPci_id() {
        return pci_id;
    }

    public void setPci_id(int pci_id) {
        this.pci_id = pci_id;
    }
}
