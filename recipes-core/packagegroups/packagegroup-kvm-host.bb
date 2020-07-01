SUMMARY = "Provides a set of tools for hosting KVM guests"

inherit packagegroup

RDEPENDS_${PN} = "\
    packagegroup-core-boot qemu \
    libvirt \
    libvirt-libvirtd \
    libvirt-virsh \
    "
