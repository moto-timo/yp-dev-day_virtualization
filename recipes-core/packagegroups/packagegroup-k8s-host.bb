SUMMARY = "Provides a set of tools for hosting k8s and container guests"

inherit packagegroup

RDEPENDS_${PN} = "\
    ca-certificates \
    conntrack-tools \
    cri-o \
    crictl \
    kubernetes \
    tzdata \
    virtual/containerd \
    virtual/runc \
    "
