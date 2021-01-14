SUMMARY = "Provides a set of tools for hosting k8s and container guests"

inherit packagegroup

RDEPENDS_${PN} = "\
    conntrack-tools \
    cri-o \
    virtual/runc \
    kubernetes \
    virtual/containerd \
    "
