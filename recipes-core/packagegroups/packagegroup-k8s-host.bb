SUMMARY = "Provides a set of tools for hosting k8s and container guests"

inherit packagegroup

RDEPENDS:${PN} = "\
    conntrack-tools \
    virtual/runc \
    kubernetes \
    virtual/containerd \
    "
