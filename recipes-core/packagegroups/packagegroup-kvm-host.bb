SUMMARY = "Provides a set of tools for hosting KVM guests"

inherit packagegroup

RDEPENDS_${PN} = "\
    cloud-init \
    packagegroup-core-boot qemu \
    libvirt \
    libvirt-libvirtd \
    libvirt-virsh \
    "
