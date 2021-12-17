SUMMARY = "Provides a set of tools for hosting KVM guests"

inherit packagegroup

RDEPENDS:${PN} = "\
    cloud-init \
    packagegroup-core-boot qemu \
    libvirt \
    libvirt-libvirtd \
    libvirt-virsh \
    "
