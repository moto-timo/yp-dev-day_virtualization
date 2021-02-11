SUMMARY = "Provides a set of tools for hosting k8s and container guests"

inherit packagegroup

RDEPENDS_${PN} = "\
    ca-certificates \
    conntrack-tools \
    cri-o \
    crictl \
    k9s \
    kernel-devsrc \
    kubernetes \
    podman \
    tzdata \
    virtual/containerd \
    virtual/runc \
    "
