SUMMARY = "Package wrapper around a minimal Xen guest image"
# This is a lie, see the license manifest inside the xen guest image
LICENSE="CLOSED"

inherit xen-guest-package

SRC_URI = "file://xen-guest-image-minimal.cfg"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_install:append() {
  install ${UNPACKDIR}/xen-guest-image-minimal.cfg ${D}${vesseldir}/
}
